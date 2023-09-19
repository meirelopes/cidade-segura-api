package com.catalisa.cidadeseguraapi.security;

import com.catalisa.cidadeseguraapi.usuario.Usuario;
import com.catalisa.cidadeseguraapi.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImplement implements UserDetailsService {
    @Autowired
    private UsuarioRepository uSuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = uSuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Esse usuário não foi encontrado!"));

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true,
                usuario.getAuthorities());
    }

}
