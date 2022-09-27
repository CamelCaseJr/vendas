package br.com.apivendas.controller;

import br.com.apivendas.dto.UsuarioDto;
import br.com.apivendas.form.UsuarioForm;
import br.com.apivendas.model.entity.Usuario;
import br.com.apivendas.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<UsuarioDto>> getUsuario(){
        List<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioDto.converterUsuario(usuarios));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<UsuarioDto>> getUsuarioPorNome(@PathVariable String nome) {
        Optional<List<Usuario>> optinalUsuario = usuarioService.findyByNome(nome);
        if (!optinalUsuario.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(UsuarioDto.converterUsuario(optinalUsuario.get()));

        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<Object> saveUsuario(@RequestBody @Valid UsuarioForm usuarioForm){
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioForm, usuario);
        usuarioService.salvar(usuario);
        return ResponseEntity.status(OK).body(UsuarioDto.converterParaDTO(usuario));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizarUsuario(@RequestParam("idUsuario") Long idUsuario){
        Optional<Usuario> optionalUsuario = usuarioService.findyById(idUsuario);

        return optionalUsuario.map(usuario -> ResponseEntity
                .status(OK)
                .body(UsuarioDto.converterParaDTO(usuarioService.salvar(optionalUsuario.get()))))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));

    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Object> deletarUsuario(@PathVariable("id") Long id){
        Optional<Usuario> optionalUsuario = usuarioService.findyById(id);

        if (optionalUsuario.isPresent()){
            usuarioService.deletar(optionalUsuario.get());
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.status(NOT_FOUND).body("erro");
    }

}
