package br.com.apivendas.service;

import br.com.apivendas.model.Item;
import br.com.apivendas.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItensService {

    private ItemRepository itemRepository;

    public ItensService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List findAll() {
        return itemRepository.findAll();
    }
    @Transactional
    public Item salvar(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> findById(Long idItem) {
        return itemRepository.findById(idItem);
    }
}
