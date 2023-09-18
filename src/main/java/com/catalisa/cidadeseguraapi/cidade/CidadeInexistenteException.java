package com.catalisa.cidadeseguraapi.cidade;

public class CidadeInexistenteException extends RuntimeException{

    public CidadeInexistenteException(){

        super("Não existe cidade cadastrada com o id informado!");

    }
}
