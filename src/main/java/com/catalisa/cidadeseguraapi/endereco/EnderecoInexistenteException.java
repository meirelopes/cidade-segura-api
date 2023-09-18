package com.catalisa.cidadeseguraapi.endereco;

public class EnderecoInexistenteException extends RuntimeException{
    public EnderecoInexistenteException(){
        super("Não existe endereco cadastrado com o id informado!");
    }
}
