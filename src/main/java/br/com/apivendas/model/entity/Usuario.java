package br.com.apivendas.model.entity;

import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Pedido;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Usuario {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;

    @ManyToOne
    @JoinColumn(name = "carrinho_de_compras_id")
    private CarrinhoDeCompras carrinhoDeCompras;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}
