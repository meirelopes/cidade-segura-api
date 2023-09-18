package com.catalisa.cidadeseguraapi.estado;

public class EstadoInexistenteException extends RuntimeException{
    public EstadoInexistenteException(){

        super("Não existe estado cadastrado com o id informado!");

    }

}
