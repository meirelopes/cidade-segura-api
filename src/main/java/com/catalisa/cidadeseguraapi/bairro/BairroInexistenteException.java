package com.catalisa.cidadeseguraapi.bairro;

public class BairroInexistenteException extends RuntimeException{
    public BairroInexistenteException(){

        super("Não existe bairro cadastrado com o id informado!");

    }
}
