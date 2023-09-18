package com.catalisa.cidadeseguraapi.localPerigoso;

import com.catalisa.cidadeseguraapi.bairro.BairroRepository;
import com.catalisa.cidadeseguraapi.endereco.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/locais-perigosos")
public class LocalPerigosoController {
    @Autowired
    private LocalPerigosoRepository localPerigosoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private BairroRepository bairroRepository;

    @GetMapping
    public ResponseEntity<?> listar() {

        var locais = localPerigosoRepository.findAll();
        var locaisPerigosoResponse = new ArrayList<LocalPerigosoResponse>();

        for (var local : locais) {
            locaisPerigosoResponse.add(LocalPerigosoResponse.toResponse(local));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(locaisPerigosoResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> consultar(@PathVariable Long id) {

        LocalPerigoso localPerigoso = getLocalPerigoso(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(LocalPerigosoResponse.toResponse(localPerigoso));
    }

    private LocalPerigoso getLocalPerigoso(Long id) {
        var localPerigoso = localPerigosoRepository.findById(id)
                .orElseThrow(LocalPerigosoInexistenteException::new);
        return localPerigoso;
    }

    @PostMapping
    public ResponseEntity<?> inserir(@Valid @RequestBody LocalPerigosoRequest request) {

        var localPerigoso = request.toModel(enderecoRepository, bairroRepository);
        return salvar(localPerigoso, HttpStatus.CREATED);

    }

    private ResponseEntity<?> salvar(LocalPerigoso localPerigoso, HttpStatus status) {

        localPerigoso = localPerigosoRepository.save(localPerigoso);

        return ResponseEntity
                .status(status)
                .body(LocalPerigosoResponse.toResponse(localPerigoso));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {

        LocalPerigoso localPerigoso = getLocalPerigoso(id);
        localPerigosoRepository.delete(localPerigoso);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @Valid @RequestBody LocalPerigosoRequest request) {
        LocalPerigoso localPerigoso = getLocalPerigoso(id);
        localPerigoso.getEndereco().setId(request.getIdEndereco());
        localPerigoso.getBairro().setId(request.getIdBairro());
        localPerigoso.setHorario(request.getHorario());
        localPerigoso.setDescricao(request.getDescricao());
        CategoriaPericulosidade categoria = CategoriaPericulosidade.fromString(request.getCategoriaPericulosidade());
        localPerigoso.setCategoriaPericulosidade(categoria);

        return salvar(localPerigoso, HttpStatus.OK);
    }

}
