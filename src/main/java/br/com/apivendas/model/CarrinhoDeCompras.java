package br.com.apivendas.model;

import br.com.apivendas.dto.ItemDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CarrinhoDeCompras {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany()
    public List<Item> itemList = new ArrayList<>();

}
