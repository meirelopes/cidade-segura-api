package com.catalisa.cidadeseguraapi.usuario;

public class UsuarioResponse {

    private Long id;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static UsuarioResponse toResponse(Usuario usuario) {

        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setUsername(usuario.getUsername());
        return usuarioResponse;
    }


}
