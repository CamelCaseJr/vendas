package br.com.apivendas.controller;

import br.com.apivendas.dto.PedidoDto;
import br.com.apivendas.form.CarrinhoForm;
import br.com.apivendas.form.PedidoForm;
import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Pedido;
import br.com.apivendas.service.CarrinhoService;
import br.com.apivendas.service.PedidoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<List<PedidoDto>> getPedidos(){
        List<Pedido> pedidos = pedidoService.findyAll();
        return ResponseEntity.status(HttpStatus.OK).body(PedidoDto.converterPedidos(pedidos));
    }
    @GetMapping("/nomeUsuario/{nome}")
    public ResponseEntity<List<PedidoDto>> getPedidosPorNome(@PathVariable String nome) {
        Optional<List<Pedido>> optionalPedido = pedidoService.findyByNome(nome);

        if (!optionalPedido.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PedidoDto.converterPedidos(optionalPedido.get()));

        }
        return new ResponseEntity<>(NOT_FOUND);
    }
    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<PedidoDto>> getPedidosPorNome(@PathVariable UUID numero) {
        Optional<List<Pedido>> optionalPedido = pedidoService.findyByNumero(numero);

        if (!optionalPedido.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(PedidoDto.converterPedidos(optionalPedido.get()));

        }
        return new ResponseEntity<>(NOT_FOUND);
    }




    @PostMapping("/salvar")
    @Transactional
    public ResponseEntity<Object> savePedido(@RequestParam("idCarrinho") Long idCarrinho){

        Optional<CarrinhoDeCompras> carrinhoOptional = carrinhoService.findById(idCarrinho);

        if (carrinhoOptional.isPresent()){
            var pedido = new Pedido();
            PedidoForm pedidoForm = PedidoForm.criarPedido(carrinhoOptional.get());
            BeanUtils.copyProperties(pedidoForm, pedido);
            System.out.println("salvar pedido");
            pedidoService.save(pedido);
            return ResponseEntity.status(HttpStatus.OK).body(PedidoDto.converterPedido(pedido));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("não foi passado o produto");
    }

    @DeleteMapping("/deletar/{id}")
    @Transactional
    public ResponseEntity<Object> deletarPedido(@PathVariable("id") Long id){
        Optional<Pedido> pedido = pedidoService.findById(id);
        if (pedido.isPresent()){
            pedidoService.deletar(id);
            return ResponseEntity.status(OK).build();
        }
        return ResponseEntity.notFound().build();

    }

}
