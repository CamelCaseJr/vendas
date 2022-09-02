package br.com.apivendas.dto;

import lombok.Data;

@Data
public class ItemDto {
    private String nome;
    private String urlItem;
    private String urlImagem;
    private String preco;
    private String quantidade;


}
