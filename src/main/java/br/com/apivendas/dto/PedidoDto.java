package br.com.apivendas.dto;

import java.time.LocalDate;

public class PedidoDto {
    private String numero;
    private String precoPedido;
    private LocalDate dataDoPedido;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPrecoPedido() {
        return precoPedido;
    }

    public void setPrecoPedido(String precoPedido) {
        this.precoPedido = precoPedido;
    }

    public LocalDate getDataDoPedido() {
        return dataDoPedido;
    }

    public void setDataDoPedido(LocalDate dataDoPedido) {
        this.dataDoPedido = dataDoPedido;
    }
}
