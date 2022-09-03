package br.com.apivendas.repository;

import br.com.apivendas.model.CarrinhoDeCompras;
import br.com.apivendas.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoDeCompras, Long> {
}
