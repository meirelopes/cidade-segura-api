package com.catalisa.cidadeseguraapi.localPerigoso;

public class LocalPerigosoInexistenteException extends RuntimeException{
    public LocalPerigosoInexistenteException(){

        super("Não existe local cadastrado com o id informado!");
    }
}
