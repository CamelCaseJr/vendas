package br.com.apivendas.controller;

import br.com.apivendas.dto.PedidoDto;
import br.com.apivendas.model.Item;
import br.com.apivendas.model.Pedido;
import br.com.apivendas.service.ItensService;
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
    private ItensService itensService;


    public PedidoController(PedidoService pedidoService, ItensService itensService) {
        this.pedidoService = pedidoService;
        this.itensService = itensService;
    }
    @GetMapping
    public ResponseEntity<List<Pedido>> getPedidos(){
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findyAll());
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Object> savePedido(@RequestBody PedidoDto pedidoDto,@RequestParam("idItem") Long idItem){

        Optional<Item> itemOptional =  itensService.findById(idItem);

        if (itemOptional.isPresent()){
            var pedido = new Pedido();
            pedido.setItens(itemOptional.get());

            BeanUtils.copyProperties(pedidoDto, pedido);
            pedidoDto.setDataDoPedido(LocalDate.now(ZoneId.of("UTC")));
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedido));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("não foi passado o produto");
    }

}
