package br.com.apivendas.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Itnes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String urlItem;
    private String UrlImagem;
    private Boolean preco;
    @ManyToOne
    private Pedido pedido;

}
