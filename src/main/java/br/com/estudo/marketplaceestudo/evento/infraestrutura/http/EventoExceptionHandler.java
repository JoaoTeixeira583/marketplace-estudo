package br.com.estudo.marketplaceestudo.evento.infraestrutura.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// para tratar os erros
@RestControllerAdvice
public class EventoExceptionHandler {

    // para tratar o erro de evento lotado
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> tratarEventoLotado(IllegalStateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // para tratar os dados invalidos de um evento
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> tratarDadosInvalidos(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
