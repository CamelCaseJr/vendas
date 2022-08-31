package br.com.apivendas.controller;

import br.com.apivendas.model.Pedido;
import br.com.apivendas.service.PedidoService;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos(){
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findyAll());
    }

}
