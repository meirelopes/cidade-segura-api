package com.catalisa.cidadeseguraapi.estado;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class EstadoRequest {
    @NotBlank
    @Schema(example = "Espírito Santo")
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
