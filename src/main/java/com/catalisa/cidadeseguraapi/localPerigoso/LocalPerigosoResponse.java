package com.catalisa.cidadeseguraapi.localPerigoso;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalPerigosoResponse {
    private Long id;
    private Long id_endereco;
    private CategoriaPericulosidade categoriaPericulosidade;
    private LocalTime horario;
    private String descricao;
    private Long id_bairro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(Long id_endereco) {
        this.id_endereco = id_endereco;
    }

    public CategoriaPericulosidade getCategoriaPericulosidade() {
        return categoriaPericulosidade;
    }

    public void setCategoriaPericulosidade(CategoriaPericulosidade categoriaPericulosidade) {
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

    public Long getId_bairro() {
        return id_bairro;
    }

    public void setId_bairro(Long id_bairro) {
        this.id_bairro = id_bairro;
    }

    public static LocalPerigosoResponse toResponse(LocalPerigoso localPerigoso){

        var localPerigosoResponse = new LocalPerigosoResponse();
        localPerigosoResponse.setId(localPerigoso.getId());
        localPerigosoResponse.setId_endereco(localPerigoso.getEndereco().getId());
        localPerigosoResponse.setId_bairro(localPerigoso.getBairro().getId());
        localPerigosoResponse.setHorario(localPerigoso.getHorario());
        localPerigosoResponse.setDescricao(localPerigoso.getDescricao());
        localPerigosoResponse.setCategoriaPericulosidade(localPerigoso.getCategoriaPericulosidade());
        return localPerigosoResponse;
    }
}
