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
    private UUID numero;

    @OneToOne
    private CarrinhoDeCompras carrinhoDeCompras;

    private LocalDate dataDoPedido;
    private String precoPedido;

    public  void setNumeroPedido(){
        UUID uuid = UUID.randomUUID();
        numero = uuid;
    }
}
