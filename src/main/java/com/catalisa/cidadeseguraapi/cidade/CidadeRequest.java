package com.catalisa.cidadeseguraapi.cidade;

import com.catalisa.cidadeseguraapi.estado.EstadoInexistenteException;
import com.catalisa.cidadeseguraapi.estado.EstadoRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class CidadeRequest {
    @NotBlank
    @Schema(example = "Vitória")
    private String nome;
    @NotNull
    @Schema(example = "1")
    private Long idEstado;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdEstado() {
        return idEstado;
    }
    public CidadeRequest(){};

    public CidadeRequest(String nome, Long idEstado) {
        this.nome = nome;
        this.idEstado = idEstado;
    }

    public Cidade toModel(EstadoRepository estadoRepository) {

//       var estado = estadoRepository.findById(idEstado);
//       if (!estado.isPresent()) {
//           throw new EstadoInexistenteException();
//       }
//       var estadoEncontrado = estado.get();

        var estado = estadoRepository.findById(idEstado)
                .orElseThrow(EstadoInexistenteException::new);

        return new Cidade(this.nome, estado);
   }


}
