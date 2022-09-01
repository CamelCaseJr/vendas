package br.com.apivendas.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;

    @ManyToOne()
    private Usuario usuario;
    private LocalDate dataDoPedido;
    private String precoPedido;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Item> itnes = new ArrayList<>();

    public void setItens(Item item){
        System.out.println("adicionando item");
         this.itnes.add(item);
    }


}
