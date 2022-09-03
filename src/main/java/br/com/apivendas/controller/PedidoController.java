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

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;
    private final CarrinhoService carrinhoService;


    public PedidoController(PedidoService pedidoService, CarrinhoService carrinhoService) {
        this.pedidoService = pedidoService;

        this.carrinhoService = carrinhoService;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos(){
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findyAll());
    }

    @PostMapping("/salvar")
    public ResponseEntity<Object> savePedido(@RequestBody PedidoDto pedidoDto, @RequestParam("idCarrinho") Long idCarrinho){

        Optional<CarrinhoDeCompras> carrinhoOptional = carrinhoService.findById(idCarrinho);

        if (carrinhoOptional.isPresent()){
            System.out.println("criar pedido");

            var pedido = new Pedido();
            CarrinhoDeCompras carrinhoDeCompras = carrinhoOptional.get();
            pedidoDto.setPrecoPedido(String.valueOf(carrinhoDeCompras.getTotal()));
            System.out.println("salvar data");
            pedidoDto.setDataDoPedido(LocalDate.now(ZoneId.of("UTC")));

            BeanUtils.copyProperties(pedidoDto, pedido);

            System.out.println("adicionar carrinho");
            pedido.setCarrinhoDeCompras(carrinhoOptional.get());


            System.out.println("salvar pedido");
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedido));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("não foi passado o produto");
    }

}
