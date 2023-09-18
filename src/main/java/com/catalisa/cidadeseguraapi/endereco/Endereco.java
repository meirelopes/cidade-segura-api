package com.catalisa.cidadeseguraapi.endereco;

import com.catalisa.cidadeseguraapi.bairro.Bairro;
import com.catalisa.cidadeseguraapi.cidade.CidadeRepository;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeRua;
    private String numero;
    private String pontoReferencia;
    @ManyToOne()
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

    public Endereco(String nomeRua, String numero, String pontoReferencia, Bairro bairro) {
        this.nomeRua = nomeRua;
        this.numero = numero;
        this.pontoReferencia = pontoReferencia;
        this.bairro = bairro;
    }

    public Endereco(){}

    public Long getId() {
        return id;
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

    public Bairro getBairro() {
        return bairro;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public boolean existeOutroEnderecoComMesmosDados(EnderecoRepository repository){

        if(repository.findByNomeRuaAndNumeroAndBairro(
                        this.getNomeRua(), this.numero, this.bairro)
                .isPresent()) {
            return true;
        }
        return false;
    }
}
