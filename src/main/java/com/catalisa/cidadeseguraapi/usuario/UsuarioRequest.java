package com.catalisa.cidadeseguraapi.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {
    @NotBlank
    private String nome;
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Usuario toModel(){

        String password = new BCryptPasswordEncoder().encode(this.password);

        return new Usuario(this.nome, this.email, this.username, password);

    }
}
