package com.catalisa.cidadeseguraapi.endereco;

import com.catalisa.cidadeseguraapi.bairro.BairroInexistenteException;
import com.catalisa.cidadeseguraapi.bairro.BairroRepository;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EnderecoRequest {
    @NotBlank
    private String nomeRua;
    @NotNull
    private String numero;
    @NotBlank
    private String pontoReferencia;
    @NotNull
    private Long idBairro;
    public EnderecoRequest(){}
    public EnderecoRequest(String nomeRua, String numero, String pontoReferencia, Long id_bairro) {
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.pontoReferencia = pontoReferencia;
        this.idBairro = id_bairro;
    }

    public void setNomeRua(String nomeRua) {
        this.nomeRua = nomeRua;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public void setIdBairro(Long idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeRua() {
        return nomeRua;
    }

    public String getNumero() {
        return numero;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public Long getIdBairro() {
        return idBairro;
    }

    public Endereco toModel(BairroRepository bairroRepository){

        var bairro = bairroRepository.findById(idBairro)
                .orElseThrow(BairroInexistenteException::new);
        return new Endereco(this.nomeRua, this.numero, this.pontoReferencia, bairro);

    }
}
