package br.com.apivendas.service;

import br.com.apivendas.model.CarrinhoDeCompras;
import br.com.apivendas.model.Item;
import br.com.apivendas.repository.CarrinhoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public Optional<CarrinhoDeCompras> findById(Long idCarrinho) {
       return carrinhoRepository.findById(idCarrinho);
    }

    public CarrinhoDeCompras save(Item item, int quantidade) {
        var carrinho = new CarrinhoDeCompras();
        carrinho.setItens(item,quantidade);
        return carrinhoRepository.save(carrinho);
    }

    public List<CarrinhoDeCompras> buscarTodos() {
        return carrinhoRepository.findAll();
    }
}
