package br.com.apivendas.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PedidoDto {
    private String precoPedido;
    private LocalDate dataDoPedido;

}


