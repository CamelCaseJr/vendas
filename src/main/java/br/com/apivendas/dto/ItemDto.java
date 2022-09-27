package br.com.apivendas.dto;

import br.com.apivendas.model.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class ItemDto {
    private Long id;
    private String nome;
    private String urlItem;
    private String urlImagem;
    private String preco;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.nome = item.getNome();
        this.urlImagem = item.getUrlImagem();
        this.urlItem = item.getUrlItem();
        this.preco = item.getPreco();
    }



    public static List<ItemDto> converterItemDto ( List<Item> itens){
        return itens.stream().map(ItemDto::new).toList();
    }

}
