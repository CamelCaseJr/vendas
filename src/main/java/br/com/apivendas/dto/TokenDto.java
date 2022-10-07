package br.com.apivendas.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    private String bearer;
    public TokenDto(String token, String bearer) {
        this.bearer = bearer;
        this.token = token;
    }
}
