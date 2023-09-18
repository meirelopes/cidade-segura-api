package com.catalisa.cidadeseguraapi.localPerigoso;

import com.catalisa.cidadeseguraapi.bairro.BairroInexistenteException;
import com.catalisa.cidadeseguraapi.bairro.BairroRepository;
import com.catalisa.cidadeseguraapi.endereco.EnderecoInexistenteException;
import com.catalisa.cidadeseguraapi.endereco.EnderecoRepository;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalPerigosoRequest {
    @NotNull
    @Schema(example = "1")
    private Long idEndereco;
    @NotBlank
    @Schema(example = "baixa")
    private String categoriaPericulosidade;
    @Schema(example = "22:30")
    private LocalTime horario;
    @NotBlank
    @Schema(example = "Local com pouca iluminação e sem movimento pela noite")
    private String descricao;
    @NotNull
    @Schema(example = "1")
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

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
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
