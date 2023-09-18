package com.catalisa.cidadeseguraapi.endereco;

public class EnderecoResponse {
    private Long id;
    private String nomeRua;
    private String numero;
    private String pontoReferencia;
    private Long id_bairro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public Long getId_bairro() {
        return id_bairro;
    }

    public void setId_bairro(Long id_bairro) {
        this.id_bairro = id_bairro;
    }
    public static EnderecoResponse toResponse(Endereco endereco){

        var enderecoResponse = new EnderecoResponse();
        enderecoResponse.setId(endereco.getId());
        enderecoResponse.setNomeRua(endereco.getNomeRua());
        enderecoResponse.setNumero(endereco.getNumero());
        enderecoResponse.setPontoReferencia(endereco.getPontoReferencia());
        enderecoResponse.setId_bairro(endereco.getBairro().getId());
        return enderecoResponse;
    }
}
