package com.catalisa.cidadeseguraapi.usuario;

import com.catalisa.cidadeseguraapi.cidade.Cidade;
import com.catalisa.cidadeseguraapi.cidade.CidadeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody UsuarioRequest request) {

            var usuario = request.toModel();
            return salvar(usuario, HttpStatus.CREATED);

        }

        private ResponseEntity<?> salvar(Usuario usuario, HttpStatus status) {
            if (usuario.existeOutroUsuarioComMesmoEmail(usuarioRepository)) {
                return ResponseEntity.badRequest().body("Já existe usuário cadastrado com mesmo e-mail..");
            } else if (usuario.existeOutroUsuarioComMesmoUsername(usuarioRepository)) {
                return ResponseEntity.badRequest().body("Já existe usuário cadastrado com mesmo username.");
            } else if (usuario.existeOutroUsuarioComMesmoPassword(usuarioRepository)) {
                return ResponseEntity.badRequest().body("Já existe usuário cadastrado com mesma senha.");
            } else {

                usuario = usuarioRepository.save(usuario);

                return ResponseEntity
                        .status(status)
                        .body(UsuarioResponse.toResponse(usuario));
            }
        }

}
