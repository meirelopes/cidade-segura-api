package com.catalisa.cidadeseguraapi.endereco;

import com.catalisa.cidadeseguraapi.bairro.BairroRepository;
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
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BairroRepository bairroRepository;

    Logger logger = LoggerFactory.getLogger(CidadeController.class);

    @GetMapping
    public ResponseEntity<?> listar() {

        var enderecos = enderecoRepository.findAll();
        var enderecosResponse = new ArrayList<EnderecoResponse>();

        for (var endereco : enderecos) {
            enderecosResponse.add(EnderecoResponse.toResponse(endereco));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(enderecosResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultar(@PathVariable Long id) {

        Endereco endereco = getEndereco(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(EnderecoResponse.toResponse(endereco));
    }

    private Endereco getEndereco(Long id) {
        var endereco = enderecoRepository.findById(id)
                .orElseThrow(EnderecoInexistenteException::new);
        return endereco;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@Valid @RequestBody EnderecoRequest request) {

        var endereco = request.toModel(bairroRepository);
        return salvar(endereco, HttpStatus.CREATED);

    }

    private ResponseEntity<?> salvar(Endereco endereco, HttpStatus status) {
        if (endereco.existeOutroEnderecoComMesmosDados(enderecoRepository)) {
            return ResponseEntity.badRequest().body("Já existe cidade com mesmo nome e estado.");
        } else {
            endereco = enderecoRepository.save(endereco);

            return ResponseEntity
                    .status(status)
                    .body(EnderecoResponse.toResponse(endereco));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        Endereco endereco = getEndereco(id);
        enderecoRepository.delete(endereco);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody EnderecoRequest request) {
        Endereco endereco = getEndereco(id);
        endereco.setNomeRua(request.getNomeRua());
        endereco.setNumero(request.getNumero());
        endereco.setPontoReferencia(request.getPontoReferencia());
        endereco.getBairro().setId(request.getIdBairro());
        return salvar(endereco, HttpStatus.OK);
    }

}
