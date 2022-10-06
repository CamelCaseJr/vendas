package br.com.apivendas.service;

import br.com.apivendas.model.entity.Usuario;
import br.com.apivendas.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public Page<Usuario> buscarTodos(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Optional<List<Usuario>> findyByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findyById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletar(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
