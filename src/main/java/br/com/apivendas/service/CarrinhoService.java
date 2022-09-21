package br.com.apivendas.service;

import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Item;
import br.com.apivendas.repository.CarrinhoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public CarrinhoDeCompras save(Item item, int quantidade) {
        var carrinho = new CarrinhoDeCompras();
        carrinho.setItens(item,quantidade);
        return carrinhoRepository.save(carrinho);
    }

    public List<CarrinhoDeCompras> buscarTodos() {
        return carrinhoRepository.findAll();
    }

    public Optional<List<Object>> findyByNome(String nome) {
        return carrinhoRepository.findByUsuarioNome(nome);
    }


    public Object delete(CarrinhoDeCompras carrinhoDeCompras, Long idItem) {
        Item i = carrinhoDeCompras.getItens().stream()
                .filter(item -> item.getId().equals(idItem))
                .findAny().get();

       carrinhoDeCompras.removerItens(i);
       return carrinhoRepository.save(carrinhoDeCompras);

    }

    public Object atualizar(Long iditem, CarrinhoDeCompras carrinhoDeCompras, int quantidade) {
            carrinhoDeCompras.getItens().stream()
                    .filter(item -> item.getId().equals(iditem))
                    .findAny().get().setQuantidade(quantidade);
            return carrinhoRepository.save(carrinhoDeCompras);
    }
}
