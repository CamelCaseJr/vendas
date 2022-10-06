package br.com.apivendas.form;

import br.com.apivendas.model.entity.Usuario;
import br.com.apivendas.model.enuns.Cidade;
import br.com.apivendas.model.enuns.Estado;
import br.com.apivendas.model.enuns.Pais;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class EnderecoForm {
    @NotBlank @NotNull @NotEmpty
    private Usuario usuario;
    @NotBlank @NotNull @NotEmpty
    private Pais pais;
    @NotBlank @NotNull @NotEmpty
    private String telefone;
    @NotBlank @NotNull @NotEmpty
    private String cep;
    @NotBlank @NotNull @NotEmpty
    private String endereco;
    @NotBlank @NotNull @NotEmpty
    private String numeroResidencia;
    @NotBlank @NotNull @NotEmpty
    private String complemento;
    @NotBlank @NotNull @NotEmpty
    private String bairro;
    @NotBlank @NotNull @NotEmpty
    private Cidade cidade;
    @NotBlank @NotNull @NotEmpty
    private Estado estado;

}
