package com.catalisa.cidadeseguraapi.localPerigoso;

import com.catalisa.cidadeseguraapi.bairro.BairroInexistenteException;
import com.catalisa.cidadeseguraapi.bairro.BairroRepository;
import com.catalisa.cidadeseguraapi.endereco.EnderecoInexistenteException;
import com.catalisa.cidadeseguraapi.endereco.EnderecoRepository;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class LocalPerigosoRequest {
    @NotNull
    private Long idEndereco;
    @NotBlank
    private String categoriaPericulosidade;

    private LocalDateTime horario;
    @NotBlank
    private String descricao;
    @NotNull
    private Long idBairro;

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCategoriaPericulosidade() {
        return categoriaPericulosidade;
    }

    public void setCategoriaPericulosidade(String categoriaPericulosidade) {
        this.categoriaPericulosidade = categoriaPericulosidade;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Long idBairro) {
        this.idBairro = idBairro;
    }

    public LocalPerigoso toModel(EnderecoRepository enderecoRepository, BairroRepository bairroRepository) {

        var endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(EnderecoInexistenteException::new);

        var bairro = bairroRepository.findById(idBairro)
                .orElseThrow(BairroInexistenteException::new);
        CategoriaPericulosidade categoria = CategoriaPericulosidade.fromString(this.categoriaPericulosidade);

        return new LocalPerigoso(endereco, categoria, this.horario, this.descricao,
                bairro);

    }


}
