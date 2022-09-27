package br.com.apivendas.controller;

import br.com.apivendas.dto.ItemDto;
import br.com.apivendas.form.ItemForm;
import br.com.apivendas.model.entity.Item;
import br.com.apivendas.service.ItensService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItensController {

    private ItensService itensService;

    public ItensController(ItensService itensService) {
        this.itensService = itensService;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<ItemDto>> getItens(){
        List<Item> itens = itensService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body( ItemDto.converterItemDto(itens));
    }


    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<Item> salvarItem(@RequestBody @Valid ItemForm itemForm){
        var item = new Item();
        BeanUtils.copyProperties(itemForm, item);
        return ResponseEntity.status(HttpStatus.OK).body(itensService.salvar(item));
    }
}
