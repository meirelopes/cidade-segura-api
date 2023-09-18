package com.catalisa.cidadeseguraapi.localPerigoso;

import com.catalisa.cidadeseguraapi.bairro.Bairro;
import com.catalisa.cidadeseguraapi.cidade.CidadeRepository;
import com.catalisa.cidadeseguraapi.endereco.Endereco;
import com.catalisa.cidadeseguraapi.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "local_perigoso")
public class LocalPerigoso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    private CategoriaPericulosidade categoriaPericulosidade;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime horario;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

    public LocalPerigoso(Endereco endereco, CategoriaPericulosidade categoriaPericulosidade,
                         LocalDateTime horario, String descricao, Bairro bairro) {
        this.endereco = endereco;
        this.categoriaPericulosidade = categoriaPericulosidade;
        this.horario = horario;
        this.descricao = descricao;
        this.bairro = bairro;
    }

    public LocalPerigoso() {
    }

    public Long getId() {
        return id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public CategoriaPericulosidade getCategoriaPericulosidade() {
        return categoriaPericulosidade;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setCategoriaPericulosidade(CategoriaPericulosidade categoriaPericulosidade) {
        this.categoriaPericulosidade = categoriaPericulosidade;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }
}
