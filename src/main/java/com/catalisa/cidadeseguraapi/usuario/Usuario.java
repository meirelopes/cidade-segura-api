package com.catalisa.cidadeseguraapi.usuario;

import com.catalisa.cidadeseguraapi.endereco.EnderecoRepository;
import com.catalisa.cidadeseguraapi.localPerigoso.LocalPerigoso;
import com.catalisa.cidadeseguraapi.security.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    public Usuario(){}

    public Usuario(String nome, String email, String username, String password) {
        this.nome = nome;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    @ManyToMany
    @JoinTable(name = "TB_USUARIOS_ROLES", joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public boolean existeOutroUsuarioComMesmoEmail(UsuarioRepository repository){

        if(repository.findByEmail(this.getEmail()).isPresent()) {

            return true;
        }
        return false;
    }
    public boolean existeOutroUsuarioComMesmoPassword(UsuarioRepository repository){

        if(repository.findByPassword(this.getPassword()).isPresent()) {

            return true;
        }
        return false;
    }
    public boolean existeOutroUsuarioComMesmoUsername(UsuarioRepository repository){

        if(repository.findByUsername(this.getUsername()).isPresent()) {

            return true;
        }
        return false;
    }

}
