package br.com.apivendas.repository;

import br.com.apivendas.model.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
