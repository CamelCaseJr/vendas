package br.com.apivendas.controller;

import br.com.apivendas.dto.UsuarioDto;
import br.com.apivendas.model.entity.Usuario;
import br.com.apivendas.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private UsuarioService usuarioService ;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Usuario>> getUsuario(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarTodos());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Object>> getUsuarioPorNome(@PathVariable String nome) {
        Optional<List<Object>> optinalUsuario = usuarioService.findyByNome(nome);
        if (!optinalUsuario.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(optinalUsuario.get());

        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioDto usuarioDto){
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuario);
        return ResponseEntity.status(OK).body(usuarioService.salvar(usuario));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarUsuario(@RequestBody UsuarioDto usuarioDto, @PathVariable("id") Long id){
        Optional<Usuario> optionalUsuario = usuarioService.findyById(id);

        return optionalUsuario.map(usuario -> ResponseEntity
                .status(OK)
                .body(usuarioService.salvar(optionalUsuario.get())))
                .orElseGet(() -> ResponseEntity.status(NOT_FOUND).body("erro"));

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable("id") Long id){
        Optional<Usuario> optionalUsuario = usuarioService.findyById(id);

        if (optionalUsuario.isPresent()){
            usuarioService.deletar(optionalUsuario.get());
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.status(NOT_FOUND).body("erro");
    }

}
