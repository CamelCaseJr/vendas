package br.com.apivendas.controller;

import br.com.apivendas.dto.UsuarioDto;
import br.com.apivendas.form.UsuarioForm;
import br.com.apivendas.model.entity.Usuario;
import br.com.apivendas.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public ResponseEntity<Page<UsuarioDto>> getUsuario(@RequestParam int page, int qtd){
        Pageable pageable = PageRequest.of(page, qtd);

        Page<Usuario> usuarios = usuarioService.buscarTodos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(UsuarioDto.pageConverterUsuario(usuarios));
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
    public ResponseEntity<UsuarioDto> saveUsuario(@RequestBody @Valid UsuarioForm usuarioForm){
        var usuario = new Usuario();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioForm.getSenha());
        usuarioForm.setSenha(senhaCriptografada);
        BeanUtils.copyProperties(usuarioForm, usuario);
        return ResponseEntity.status(OK).body(UsuarioDto.converterParaDTO(usuarioService.salvar(usuario)));
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
