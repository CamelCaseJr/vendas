package br.com.apivendas.dto;

import br.com.apivendas.model.entity.Endereco;
import br.com.apivendas.model.entity.Usuario;
import br.com.apivendas.model.enuns.Cidade;
import br.com.apivendas.model.enuns.Estado;
import br.com.apivendas.model.enuns.Pais;

import java.util.List;

public class EnderecoDto {

    private Usuario usuario;
    private Pais pais;
    private String telefone;
    private String cep;
    private String endereco;
    private String numeroResidencia;
    private String complemento;
    private String bairro;
    private Cidade cidade;
    private Estado estado;

    public EnderecoDto(Endereco endereco) {
        this.usuario = endereco.getUsuario();
        this.pais = endereco.getPais();
        this.telefone = endereco.getTelefone();
        this.cep = endereco.getCep();
        this.endereco = endereco.getEndereco();
        this.numeroResidencia = endereco.getNumeroResidencia();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.estado = endereco.getEstado();
    }

    public static List<EnderecoDto> converterListEnderecoParaListEnderecoDto(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoDto::new).toList();

    }

    public static EnderecoDto converterEnderecoParaEnderecoDto(Endereco endereco) {
        return new EnderecoDto(endereco);
    }
}
