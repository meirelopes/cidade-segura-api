package com.catalisa.cidadeseguraapi.usuario;

public class UsuarioInexistenteException extends RuntimeException{
    public UsuarioInexistenteException(){
        super("Não existe usuário cadastrado com o id informado!");
    }
}
