package br.com.apivendas.model.entity;

import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Pedido;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Usuario implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfils = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "carrinho_de_compras_id")
    private CarrinhoDeCompras carrinhoDeCompras;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
