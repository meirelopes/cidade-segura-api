package com.catalisa.cidadeseguraapi.cidade;

import com.catalisa.cidadeseguraapi.estado.Estado;

import javax.persistence.*;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne()
    @JoinColumn(name = "id_estado")
    private Estado estado;

    public Cidade(String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;
    }
    public Cidade(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean existeOutraCidadeComMesmosDados(CidadeRepository repository){

        if(repository.findByNomeAndEstado(
                        this.getNome(), this.estado)
                .isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado=" + estado +
                '}';
    }

    public void atualizaDados(CidadeRequest request){

        this.nome = request.getNome();
        this.estado.getId();

    }
}
