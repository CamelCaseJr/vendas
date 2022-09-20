package br.com.apivendas.controller;

import br.com.apivendas.dto.PedidoDto;
import br.com.apivendas.model.CarrinhoDeCompras;
import br.com.apivendas.model.Pedido;
import br.com.apivendas.service.CarrinhoService;
import br.com.apivendas.service.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController()
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;
    private final CarrinhoService carrinhoService;


    public PedidoController(PedidoService pedidoService, CarrinhoService carrinhoService) {
        this.pedidoService = pedidoService;

        this.carrinhoService = carrinhoService;
    }
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Pedido>> getPedidos(){
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findyAll());
    }
    @GetMapping("/nomeUsuario/{nome}")
    public ResponseEntity<List<Object>> getPedidosPorNome(@PathVariable String nome) {
        Optional<List<Object>> optionalPedido = pedidoService.findyByNome(nome);
        if (!optionalPedido.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalPedido.get());

        }
        return new ResponseEntity<>(NOT_FOUND);
    }
    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<Object>> getPedidosPorNome(@PathVariable UUID numero) {
        Optional<List<Object>> optionalPedido = pedidoService.findyByNumero(numero);
        if (!optionalPedido.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalPedido.get());

        }
        return new ResponseEntity<>(NOT_FOUND);
    }




    @PostMapping("/salvar")
    public ResponseEntity<Object> savePedido(@RequestBody PedidoDto pedidoDto, @RequestParam("idCarrinho") Long idCarrinho){

        Optional<CarrinhoDeCompras> carrinhoOptional = carrinhoService.findById(idCarrinho);

        if (carrinhoOptional.isPresent()){
            var pedido = new Pedido();
            pedidoService.criarPedido(carrinhoOptional, pedidoDto, pedido);
            BeanUtils.copyProperties(pedidoDto, pedido);

            System.out.println("salvar pedido");
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedido));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("não foi passado o produto");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarPedido(@PathVariable("id") Long id){
        Optional<Pedido> pedido = pedidoService.findById(id);
        if (pedido.isPresent()){
            pedidoService.deletar(id);
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.notFound().build();

    }

}
