package br.com.apivendas.form;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ItemForm {
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String urlItem;
    @NotNull @NotEmpty
    private String urlImagem;
    @NotNull @NotEmpty
    private String preco;



}
