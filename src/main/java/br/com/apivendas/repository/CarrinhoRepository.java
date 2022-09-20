package br.com.apivendas.repository;

import br.com.apivendas.model.CarrinhoDeCompras;
import br.com.apivendas.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoDeCompras, Long> {
    Optional<List<Object>> findByUsuarioNome(String nome);

}
