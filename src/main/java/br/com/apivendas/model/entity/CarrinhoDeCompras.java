package br.com.apivendas.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CarrinhoDeCompras {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Double total;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Item> itens = new ArrayList<>();
    
    public void setItens(Item item, int quantidade){
        item.setQuantidade(quantidade);
        total = Double.parseDouble(item.getPreco()) * quantidade;
        itens.add(item);
    }

    public Boolean removerItens(Item item){
        return itens.remove(item);
    }

}
