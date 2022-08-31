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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private LocalDate dataDoPedido;
    private String precoPedido;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Itnes> itnes = new ArrayList<>();

    public void setItens(Itnes item){
        System.out.println("adicionando item");
         this.itnes.add(item);
         item.setPedido(this);
    }


}
