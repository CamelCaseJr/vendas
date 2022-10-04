package br.com.apivendas.controller;

import br.com.apivendas.dto.EnderecoDto;
import br.com.apivendas.form.EnderecoForm;
import br.com.apivendas.model.entity.Endereco;
import br.com.apivendas.service.EnderecoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<EnderecoDto>> listarTodos(){
        List<Endereco> enderecos = enderecoService.findyAll();
        return ResponseEntity.status(HttpStatus.OK).body(EnderecoDto.converterListEnderecoParaListEnderecoDto(enderecos));
    }


    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<EnderecoDto> saveEndereco(@RequestBody @Valid EnderecoForm enderecoForm ){


        var endereco = new Endereco();
        BeanUtils.copyProperties(enderecoForm, endereco);
        System.out.println("salvar endereco");
        return ResponseEntity.status(HttpStatus.OK).
                body(EnderecoDto.converterEnderecoParaEnderecoDto(enderecoService.save(endereco)));

    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Object> deletarEndereco(@PathVariable("id") Long id){
        Optional<Endereco> endereco = enderecoService.findById(id);
        if (endereco.isPresent()){
            enderecoService.deletar(endereco.get());
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.notFound().build();

    }
}
