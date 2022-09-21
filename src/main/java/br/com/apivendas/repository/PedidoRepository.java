package br.com.apivendas.repository;

import br.com.apivendas.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<List<Object>> findByCarrinhoDeComprasUsuarioNome(String nome);

    Optional<List<Object>> findByNumero(UUID numero);

}
