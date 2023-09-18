package com.catalisa.cidadeseguraapi.bairro;

import com.catalisa.cidadeseguraapi.localPerigoso.LocalPerigoso;

import java.util.List;

public class BairroResponse {
    private Long id;
    private String nome;
    private Long idCidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Long idCidade) {
        this.idCidade = idCidade;
    }

    public static BairroResponse toResponse(Bairro bairro){

        var bairroResponse = new BairroResponse();
        bairroResponse.setId(bairro.getId());
        bairroResponse.setNome(bairro.getNome());
        bairroResponse.setIdCidade(bairro.getCidade().getId());
        return bairroResponse;
    }
}
