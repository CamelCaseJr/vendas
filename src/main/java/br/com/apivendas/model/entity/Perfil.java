package br.com.apivendas.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "perfil")
@Data
public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;


    @Override
    public String getAuthority() {
        return this.nome;
    }
}