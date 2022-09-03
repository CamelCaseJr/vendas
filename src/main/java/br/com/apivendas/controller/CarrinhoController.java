package br.com.apivendas.controller;

import br.com.apivendas.model.CarrinhoDeCompras;
import br.com.apivendas.model.Item;
import br.com.apivendas.service.CarrinhoService;
import br.com.apivendas.service.ItensService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;
    private final ItensService itensService;

    public CarrinhoController(CarrinhoService carrinhoService, ItensService itensService) {
        this.carrinhoService = carrinhoService;
        this.itensService = itensService;
    }
    public  ResponseEntity<List<CarrinhoDeCompras>> getCarrinho(){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.buscarTodos());
    }

    @PostMapping("/salvar")
    // vai receber um item e adicionar na lista
    public ResponseEntity<Object> salvarCarrinhoDeCompras(@RequestParam("idItem") Long idItem,
                                                          @RequestParam("quantidade") int quantidade){

        Optional<Item> itemOptional = itensService.findById(idItem);

        if (itemOptional.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(carrinhoService.save(itemOptional.get(),quantidade));
        }
        return ResponseEntity.status(HttpStatus.OK).body("erro");
    }
}
