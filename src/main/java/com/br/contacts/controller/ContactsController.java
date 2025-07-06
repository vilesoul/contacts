package com.br.contacts.controller;

import com.br.contacts.model.Contact;
import com.br.contacts.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private static final Logger logger = Logger.getLogger(ContactsController.class.getName());
    private final ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<String> insertContact(@Valid @RequestBody Contact contact,
                                                 BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest()
                    .body("invalid data: " + result.getAllErrors());
        }

        logger.info("Including contact: " + contact.getName());
        contactService.insertContact(contact);

        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable int id,
            @Valid @RequestBody Contact contact,
                                                 BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest()
                    .body("invalid data: " + result.getAllErrors());
        }

        logger.info("Updating contact: " + id);
        contactService.updateContact(id,contact);

        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable int id){
        logger.info("Deleting contact: " + id);
        contactService.deleteContact(id);

        return ResponseEntity
                .ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> findContactById(
            @PathVariable int id){
        logger.info("Finding contact: " + id);
       Contact contact = contactService.findContactById(id);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(contact);
    }


    @GetMapping
    public ResponseEntity<List<Contact>> findAllContacts(){
        logger.info("Finding all contacts");
        List<Contact> contacts = contactService.findAllContacts();

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(contacts);
    }
}
