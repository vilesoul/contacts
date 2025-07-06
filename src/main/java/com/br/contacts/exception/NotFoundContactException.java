package com.br.contacts.exception;

public class NotFoundContactException extends RuntimeException {
    public NotFoundContactException(Integer id){
        super("Not Found Contact with " + id);
    }
}
