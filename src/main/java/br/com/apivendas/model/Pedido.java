package br.com.apivendas.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID numero;

    @ManyToOne()
    private Usuario usuario;
    private LocalDate dataDoPedido;
    private String precoPedido;
    private int quantidade;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Item> itnes = new ArrayList<>();

    public void setItens(Item item){
        System.out.println("adicionando item");
         this.itnes.add(item);
    }


}
