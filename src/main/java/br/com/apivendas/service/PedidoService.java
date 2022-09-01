package br.com.apivendas.service;

import br.com.apivendas.model.Pedido;
import br.com.apivendas.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
