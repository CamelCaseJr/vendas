package br.com.apivendas.model;

import br.com.apivendas.dto.ItemDto;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CarrinhoDeCompras {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario usuario;
    private Double total;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Item> itens = new ArrayList<>();
    
    public void setItens(Item item, int quantidade){
        item.setQuantidade(quantidade);
        total = Double.parseDouble(item.getPreco()) * quantidade;
        itens.add(item);
    }

}
