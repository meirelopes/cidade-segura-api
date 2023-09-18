package com.catalisa.cidadeseguraapi.cidade;

public class CidadeResponse {
    private Long id;
    private String nome;

    private Long id_estado;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId_estado(Long id_estado) {
        this.id_estado = id_estado;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public Long getId_estado() {
        return id_estado;
    }

    public static CidadeResponse toResponse(Cidade cidade){

        var cidadeResponse = new CidadeResponse();
        cidadeResponse.setId(cidade.getId());
        cidadeResponse.setNome(cidade.getNome());
        cidadeResponse.setId_estado(cidade.getEstado().getId());
        return cidadeResponse;

    }

}
