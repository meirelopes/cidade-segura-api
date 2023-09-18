package com.catalisa.cidadeseguraapi.bairro;

import com.catalisa.cidadeseguraapi.cidade.CidadeInexistenteException;
import com.catalisa.cidadeseguraapi.cidade.CidadeRepository;
import com.catalisa.cidadeseguraapi.estado.EstadoInexistenteException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BairroRequest {
    @NotBlank
    private String nome;
    @NotNull
    private Long idCidade;

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

    public Bairro toModel(CidadeRepository cidadeRepository){
        var cidade = cidadeRepository.findById(idCidade)
                .orElseThrow(CidadeInexistenteException::new);

        return new Bairro(this.getNome(), cidade);

    }
}
