package br.com.apivendas.dto;

import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Pedido;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class PedidoDto {

    private UUID numero;

    private String precoPedido;

    private LocalDate dataDoPedido;

    private CarrinhoDeCompras carrinhoDeCompras;

    public PedidoDto(Pedido pedido) {
        this.precoPedido = pedido.getPrecoPedido();
        this.dataDoPedido = pedido.getDataDoPedido();
        this.numero = pedido.getNumero();
        this.carrinhoDeCompras = pedido.getCarrinhoDeCompras();
    }

    public static List<PedidoDto> converterPedidos(List<Pedido> pedidos) {
        return pedidos.stream().map(PedidoDto::new).toList();
    }

    public static PedidoDto converterPedido(Pedido pedido) {
        return new PedidoDto(pedido);
    }
}


