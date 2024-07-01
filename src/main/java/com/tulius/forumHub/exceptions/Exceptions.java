package com.tulius.forumHub.exceptions;

import com.tulius.forumHub.dto.status.DadosErros;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.IllegalFormatException;
import java.util.List;

@RestControllerAdvice
public class Exceptions {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratar400(MethodArgumentNotValidException exception){
        List<FieldError> erros= exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosAvaliacao::new));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<DadosErroDuplicata>duplicata(SQLFeatureNotSupportedException exception){
          return ResponseEntity.status(HttpStatus.CONFLICT).body(new DadosErroDuplicata(exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> campoInvalido(IllegalFormatException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> uuidInvalido(MethodArgumentTypeMismatchException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DadosErros("ID inválido"));
    }

    public record DadosErrosAvaliacao(String campo, String mensagem){
        public DadosErrosAvaliacao(FieldError fieldError){
            this(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }

    public record  DadosErroDuplicata(String error){}
}
