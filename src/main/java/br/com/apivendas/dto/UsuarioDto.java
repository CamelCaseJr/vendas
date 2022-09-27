package br.com.apivendas.dto;

import br.com.apivendas.model.entity.CarrinhoDeCompras;
import br.com.apivendas.model.entity.Pedido;
import br.com.apivendas.model.entity.Usuario;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UsuarioDto {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private CarrinhoDeCompras carrinhoDeCompras;
    private Pedido pedido;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.carrinhoDeCompras = usuario.getCarrinhoDeCompras();
        this.pedido = usuario.getPedido();

    }

    public static List<UsuarioDto> converterUsuario(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).toList();

    }

    public static UsuarioDto converterParaDTO(Usuario usuario) {
        return new UsuarioDto(usuario);
    }
}
