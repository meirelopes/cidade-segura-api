package com.catalisa.cidadeseguraapi.estado;

public class EstadoResponse {
    private Long id;
    private String nome;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public static EstadoResponse toResponse(Estado estado){

        var estadoResponse = new EstadoResponse();
        estadoResponse.setId(estado.getId());
        estadoResponse.setNome(estado.getNome());
        return estadoResponse;
    }

}
