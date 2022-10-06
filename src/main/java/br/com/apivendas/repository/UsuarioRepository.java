package br.com.apivendas.repository;

import br.com.apivendas.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<List<Usuario>> findByNome(String nome);

    Optional<Usuario> findByEmail(String username);
}
