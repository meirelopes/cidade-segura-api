package com.catalisa.cidadeseguraapi.usuario;

import com.catalisa.cidadeseguraapi.bairro.Bairro;
import com.catalisa.cidadeseguraapi.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByPassword(String password);



}
