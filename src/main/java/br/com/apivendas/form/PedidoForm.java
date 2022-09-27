package br.com.apivendas.form;

import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Pedido;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@Data
public class PedidoForm {
    @NotEmpty @NotNull
    private UUID numero;
    @NotEmpty @NotNull
    private String precoPedido;
    @NotEmpty @NotNull
    private LocalDate dataDoPedido;
    @NotEmpty @NotNull
    private CarrinhoDeCompras carrinhoDeCompras;

    public PedidoForm(CarrinhoDeCompras carrinhoDeCompras) {
        this.numero = UUID.randomUUID();
        this.dataDoPedido = LocalDate.now(ZoneId.of("UTC"));
        this.carrinhoDeCompras = carrinhoDeCompras;
        this.precoPedido = String.valueOf(carrinhoDeCompras.getTotal());
    }

    public static PedidoForm criarPedido(CarrinhoDeCompras carrinhoOptional) {
        return new PedidoForm(carrinhoOptional);
    }
}


