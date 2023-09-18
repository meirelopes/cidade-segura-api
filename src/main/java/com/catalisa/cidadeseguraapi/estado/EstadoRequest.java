package com.catalisa.cidadeseguraapi.estado;

import javax.validation.constraints.NotBlank;

public class EstadoRequest {
    @NotBlank
    private String nome;

    public EstadoRequest(String nome) {
        this.nome = nome;
    }

    public EstadoRequest(){};

    public String getNome() {
        return nome;
    }
    public Estado toModel(){

        return new Estado(this.nome);

    }
}
