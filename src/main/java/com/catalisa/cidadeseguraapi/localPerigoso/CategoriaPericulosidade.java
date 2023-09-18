package com.catalisa.cidadeseguraapi.localPerigoso;

public enum CategoriaPericulosidade {
    ALTA, MEDIA, BAIXA;
    public static CategoriaPericulosidade fromString(String categoria) {
        try {
            return CategoriaPericulosidade.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria de periculosidade inválida: " + categoria);
        }
    }

}
