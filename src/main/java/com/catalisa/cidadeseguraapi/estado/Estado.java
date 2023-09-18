package com.catalisa.cidadeseguraapi.estado;

import com.catalisa.cidadeseguraapi.cidade.CidadeRepository;

import javax.persistence.*;

@Entity
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Estado(String nome) {
        this.nome = nome;
    }

    public Estado() {}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean existeOutroEstadoComMesmosDados(EstadoRepository repository){

        if(repository.findByNome(this.getNome()).isPresent()) {

            return true;
        }
        return false;
    }
    public void atualizaDados(EstadoRequest request) {
        this.nome = request.getNome();
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
