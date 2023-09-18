package com.catalisa.cidadeseguraapi.bairro;

import com.catalisa.cidadeseguraapi.cidade.Cidade;
import com.catalisa.cidadeseguraapi.cidade.CidadeRepository;
import com.catalisa.cidadeseguraapi.localPerigoso.LocalPerigoso;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bairro")
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
    @OneToMany(mappedBy = "bairro")
    private List<LocalPerigoso> locais = new ArrayList<>();

    public Bairro(String nome, Cidade cidade, List<LocalPerigoso> locais) {
        this.nome = nome;
        this.cidade = cidade;
        this.locais = locais;
    }

    public Bairro() {}

    public Bairro(String nome, Cidade cidade) {
        this.nome = nome;
        this.cidade=cidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public List<LocalPerigoso> getLocais() {
        return locais;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public void setLocais(List<LocalPerigoso> locais) {
        this.locais = locais;
    }
    public boolean existeOutroBairroComMesmosDados(BairroRepository repository){

        if(repository.findByNomeAndCidade(
                        this.getNome(), this.cidade)
                .isPresent()) {
            return true;
        }
        return false;
    }

}
