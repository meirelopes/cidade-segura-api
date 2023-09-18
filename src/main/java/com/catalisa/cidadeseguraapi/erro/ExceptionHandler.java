package com.catalisa.cidadeseguraapi.erro;

import com.catalisa.cidadeseguraapi.bairro.BairroInexistenteException;
import com.catalisa.cidadeseguraapi.cidade.CidadeInexistenteException;
import com.catalisa.cidadeseguraapi.endereco.EnderecoInexistenteException;
import com.catalisa.cidadeseguraapi.estado.EstadoInexistenteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleDatabaseErrors(RuntimeException e, WebRequest request) {


        Map<String, Object> body = Map.of(
                "status", 500,
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", e.getLocalizedMessage()
        );
        return ResponseEntity
                .internalServerError().body(body);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({
            BairroInexistenteException.class, CidadeInexistenteException.class,
            EnderecoInexistenteException.class, EstadoInexistenteException.class})
    public ResponseEntity<?> handleDatabaseErrorsRequest(RuntimeException e, WebRequest request) {


        Map<String, Object> body = Map.of(
                "status", 402,
                "path", request.getDescription(false).replace("uri=", ""),
                "timestamp", LocalDateTime.now(),
                "message", e.getMessage()
        );
        return ResponseEntity.unprocessableEntity().body(body);
    }
}

