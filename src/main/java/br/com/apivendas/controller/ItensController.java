package br.com.apivendas.controller;

import br.com.apivendas.dto.ItemDto;
import br.com.apivendas.model.Item;
import br.com.apivendas.service.ItensService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItensController {

    private ItensService itensService;

    public ItensController(ItensService itensService) {
        this.itensService = itensService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItens(){
        return ResponseEntity.status(HttpStatus.OK).body(itensService.findAll());
    }


    @PostMapping("/salvar")
    public ResponseEntity<Item> salvarItem(@RequestBody ItemDto itemDto){
        var item = new Item();
        BeanUtils.copyProperties(itemDto, item);
        return ResponseEntity.status(HttpStatus.OK).body(itensService.salvar(item));
    }
}
