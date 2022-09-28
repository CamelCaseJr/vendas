package br.com.apivendas.controller;

import br.com.apivendas.dto.CarrinhoDto;
import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Item;
import br.com.apivendas.service.CarrinhoService;
import br.com.apivendas.service.ItensService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;
    private final ItensService itensService;

    public CarrinhoController(CarrinhoService carrinhoService, ItensService itensService) {
        this.carrinhoService = carrinhoService;
        this.itensService = itensService;
    }
    @GetMapping("/buscarTodos")
    public  ResponseEntity<List<CarrinhoDto>> getCarrinho(){
        return ResponseEntity.status(HttpStatus.OK).body(CarrinhoDto.converterListParaDto(carrinhoService.buscarTodos()));
    }

    @GetMapping("/nomeUsuario/{nome}")
    public ResponseEntity<List<CarrinhoDto>> getPedidosPorNome(@PathVariable String nome) {
        Optional<List<CarrinhoDeCompras>> optionalCarrinho = carrinhoService.findyByNome(nome);
        if (!optionalCarrinho.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(CarrinhoDto.converterListParaDto(optionalCarrinho.get()));

        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @PostMapping("/salvar")
    @Transactional
    // vai receber um item e adicionar na lista
    public ResponseEntity<CarrinhoDto> salvarCarrinhoDeCompras(@RequestParam("idItem") Long idItem,
                                                          @RequestParam("quantidade") int quantidade){

        Optional<Item> itemOptional = itensService.findById(idItem);

        if (itemOptional.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CarrinhoDto.converterParaDto(carrinhoService.save(itemOptional.get(),quantidade)));
        }
        return new  ResponseEntity<>(NOT_FOUND);
    }
    @PostMapping("/atualizar")
    @Transactional
    public ResponseEntity<CarrinhoDto> atualizarCarrinho(
            @RequestParam("idItem") Long idItem,
            @RequestParam("idcarrinho") Long idCarrinho,
            @RequestParam("quantidade") int quantidade){

        Optional<CarrinhoDeCompras> optionalCarrinhoDeCompras = carrinhoService.findById(idCarrinho);

        return optionalCarrinhoDeCompras.map(carrinhoDeCompras -> ResponseEntity
                .status(HttpStatus.OK)
                .body(CarrinhoDto.converterParaDto(carrinhoService.atualizar(idItem, optionalCarrinhoDeCompras.get(), quantidade))))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));

    }



    @DeleteMapping("/deletarItemCarrinho/{idItem}")
    @Transactional
    public ResponseEntity<Object> deletarItensCarrinho(@RequestParam("idItem") Long idItem,
                                                       @RequestParam("idcarrinho") Long idCarrinho){
        Optional<CarrinhoDeCompras> optionalCarrinhoDeCompras = carrinhoService.findById(idCarrinho);

        if(optionalCarrinhoDeCompras.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(carrinhoService.delete(optionalCarrinhoDeCompras.get(), idItem));
        }

        return ResponseEntity.notFound().build();

    }
}
