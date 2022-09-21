package br.com.apivendas.model.entity;

import br.com.apivendas.model.enuns.Cidade;
import br.com.apivendas.model.enuns.Estado;
import br.com.apivendas.model.enuns.Pais;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
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
}
