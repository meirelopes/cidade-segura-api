package com.catalisa.cidadeseguraapi.cidade;

import com.catalisa.cidadeseguraapi.estado.EstadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;

    Logger logger = LoggerFactory.getLogger(CidadeController.class);

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> listar() {

        var cidades = cidadeRepository.findAll();
        var cidadesResponse = new ArrayList<CidadeResponse>();

        for (var cidade : cidades) {
            cidadesResponse.add(CidadeResponse.toResponse(cidade));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cidadesResponse);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> consultar(@PathVariable Long id) {

        Cidade cidade = getCidade(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(CidadeResponse.toResponse(cidade));
    }

    private Cidade getCidade(Long id) {
        var cidade = cidadeRepository.findById(id)
                .orElseThrow(CidadeInexistenteException::new);
        return cidade;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> inserir(@Valid @RequestBody CidadeRequest request) {
        logger.info("TESTE");

        var cidade = request.toModel(estadoRepository);
        return salvar(cidade, HttpStatus.CREATED);

    }

    private ResponseEntity<?> salvar(Cidade cidade, HttpStatus status) {
        if (cidade.existeOutraCidadeComMesmosDados(cidadeRepository)) {
            return ResponseEntity.badRequest().body("Já existe cidade com mesmo nome e estado.");
        } else {
            cidade = cidadeRepository.save(cidade);

            return ResponseEntity
                    .status(status)
                    .body(CidadeResponse.toResponse(cidade));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> excluir(@PathVariable Long id){

        Cidade cidade = getCidade(id);
        cidadeRepository.delete(cidade);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody CidadeRequest request){
        Cidade cidade = getCidade(id);
        cidade.setNome(request.getNome());
        cidade.getEstado().setId(request.getIdEstado());
        return salvar(cidade, HttpStatus.OK);
    }

}
