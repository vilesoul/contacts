package com.br.contacts.exception;

import com.br.contacts.model.ErrorDetails;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOGGER = Logger.getLogger(ExceptionControllerAdvice.class.getName());
    @ExceptionHandler({NotFoundContactException.class})
    public ResponseEntity<ErrorDetails> exceptionNotFoundContactHandler
            (NotFoundContactException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        LOGGER.log(Level.SEVERE, "Error: " + ex.getMessage(),ex);
        return ResponseEntity.badRequest()
                .body(errorDetails);
    }

    @ExceptionHandler(NotFoundAnyContactException.class)
    public ResponseEntity<ErrorDetails> exceptionNotFoundAnyContactHandler
            (NotFoundAnyContactException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setMessage(ex.getMessage());
        LOGGER.log(Level.SEVERE, "Error: " + ex.getMessage(),ex);
        return ResponseEntity.badRequest()
                .body(errorDetails);
    }

    @ExceptionHandler({IOException.class, SQLException.class})
    public ResponseEntity<ErrorDetails> handleIOExceptionAndSQLException(Exception ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setMessage("An error occurred");
        LOGGER.log(Level.SEVERE, "Error: " + ex.getMessage(),ex);
        return ResponseEntity.badRequest()
                .body(errorDetails);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorDetails> handleEmptyResultDataAccessException
            (EmptyResultDataAccessException ex){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDetails.setMessage("Contact Not Found");
        LOGGER.log(Level.SEVERE, "Error: " + ex.getMessage(),ex);
        return ResponseEntity.badRequest()
                .body(errorDetails);
    }

}
