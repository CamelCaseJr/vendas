package br.com.apivendas.service;

import br.com.apivendas.dto.PedidoDto;
import br.com.apivendas.form.PedidoForm;
import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Pedido;
import br.com.apivendas.repository.PedidoRepository;
import ch.qos.logback.core.joran.util.beans.BeanUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> findyAll() {
        return pedidoRepository.findAll();

    }
    public Object save(Pedido pedido) {
       return pedidoRepository.save(pedido);
    }

    public Optional<List<Pedido>> findyByNome(String nome) {
        return pedidoRepository.findByCarrinhoDeComprasUsuarioNome(nome);
    }

    public Optional<List<Pedido>> findyByNumero(UUID numero) {
        return pedidoRepository.findByNumero(numero);
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
