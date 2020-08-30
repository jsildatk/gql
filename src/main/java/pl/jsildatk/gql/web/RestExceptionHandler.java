package pl.jsildatk.gql.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.jsildatk.gql.dto.ErrorMessage;
import pl.jsildatk.gql.syntax.QueryException;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {
    
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleQueryException(QueryException e) {
        log.warn(Arrays.toString(e.getStackTrace()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }
    
}
