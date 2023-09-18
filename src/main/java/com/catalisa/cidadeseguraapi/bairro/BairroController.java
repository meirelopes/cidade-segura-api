package com.catalisa.cidadeseguraapi.bairro;

import com.catalisa.cidadeseguraapi.cidade.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/bairros")
public class BairroController {
    @Autowired
    private BairroRepository bairroRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    Logger logger = LoggerFactory.getLogger(CidadeController.class);

    @GetMapping
    public ResponseEntity<?> listar() {

        var bairros = bairroRepository.findAll();
        var bairrosResponse = new ArrayList<BairroResponse>();

        for (var bairro : bairros) {
            bairrosResponse.add(BairroResponse.toResponse(bairro));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bairrosResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultar(@PathVariable Long id) {

        Bairro bairro = getBairro(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(BairroResponse.toResponse(bairro));
    }

    private Bairro getBairro(Long id) {
        var bairro = bairroRepository.findById(id)
                .orElseThrow(BairroInexistenteException::new);
        return bairro;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@Valid @RequestBody BairroRequest request) {

        var bairro = request.toModel(cidadeRepository);
        return salvar(bairro, HttpStatus.CREATED);

    }

    private ResponseEntity<?> salvar(Bairro bairro, HttpStatus status) {
        if (bairro.existeOutroBairroComMesmosDados(bairroRepository)) {
            return ResponseEntity.badRequest().body("Já existe bairro com mesmo nome e cidade.");
        } else {
            bairro = bairroRepository.save(bairro);

            return ResponseEntity
                    .status(status)
                    .body(BairroResponse.toResponse(bairro));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id){

        Bairro bairro = getBairro(id);
        bairroRepository.delete(bairro);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody BairroRequest request){
        Bairro bairro = getBairro(id);
        bairro.setNome(request.getNome());
        bairro.getCidade().setId(request.getIdCidade());

        return salvar(bairro, HttpStatus.OK);
    }
}
