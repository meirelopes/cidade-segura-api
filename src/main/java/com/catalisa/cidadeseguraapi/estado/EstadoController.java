package com.catalisa.cidadeseguraapi.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> listar() {

        var estados = estadoRepository.findAll();
        var estadosResponse = new ArrayList<EstadoResponse>();

        for (var estado : estados) {
            estadosResponse.add(EstadoResponse.toResponse(estado));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(estadosResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> consultar(@PathVariable Long id){

        Estado estado = getEstado(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EstadoResponse.toResponse(estado));
    }

    private Estado getEstado(Long id) {
        var estado = estadoRepository.findById(id)
                .orElseThrow(EstadoInexistenteException::new);
        return estado;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> inserir(@Valid @RequestBody EstadoRequest request){

        var estado = request.toModel();
        return salvar(estado, HttpStatus.CREATED);

    }

    private ResponseEntity<?> salvar(Estado estado, HttpStatus status) {
        if(estado.existeOutroEstadoComMesmosDados(estadoRepository)){
            return ResponseEntity.badRequest().body("Já existe estado com o mesmo nome.");
        }else{
            estado = estadoRepository.save(estado);

            return ResponseEntity
                    .status(status)
                    .body(EstadoResponse.toResponse(estado));
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> excluir(@PathVariable Long id){

        Estado estado = getEstado(id);
        estadoRepository.delete(estado);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody EstadoRequest request){

        Estado estado = getEstado(id);
        estado.atualizaDados(request);
        return salvar(estado, HttpStatus.OK);

    }

}
