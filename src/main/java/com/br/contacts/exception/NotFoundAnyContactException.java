package com.br.contacts.exception;

public class NotFoundAnyContactException extends RuntimeException{
    public NotFoundAnyContactException(){
        super("No contacts found");
    }
}
