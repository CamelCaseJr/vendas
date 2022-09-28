package br.com.apivendas.dto;

import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Item;
import br.com.apivendas.model.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoDto {
    private Long id;
    private Usuario usuario;
    private Double total;
    private List<Item> itens = new ArrayList<>();

    public CarrinhoDto(CarrinhoDeCompras carrinhoDeCompras) {
        this.id = carrinhoDeCompras.getId();
        this.usuario = carrinhoDeCompras.getUsuario();
        this.total = carrinhoDeCompras.getTotal();
        carrinhoDeCompras.getItens().stream().map(item -> itens.add(item));

    }

    public static List<CarrinhoDto> converterListParaDto(List<CarrinhoDeCompras> carrinhoDeCompras) {
        return carrinhoDeCompras.stream().map(CarrinhoDto::new).toList();

    }


    public static CarrinhoDto converterParaDto(CarrinhoDeCompras carrinhoDeCompras) {
        return new CarrinhoDto(carrinhoDeCompras);
    }
}
