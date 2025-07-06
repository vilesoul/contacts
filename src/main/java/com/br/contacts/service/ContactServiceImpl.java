package com.br.contacts.service;

import com.br.contacts.exception.NotFoundAnyContactException;
import com.br.contacts.exception.NotFoundContactException;
import com.br.contacts.model.Contact;
import com.br.contacts.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }
    @Override
    public void insertContact(Contact contact) {
        contactRepository.insertContact(contact);

    }

    @Override
    public Contact updateContact(int id, Contact contact) {
        Contact contactQuery = contactRepository.updateContact(id, contact);
         if(contactQuery == null){
             throw new NotFoundContactException(id);
         }
         return contactQuery;
    }

    @Override
    public void deleteContact(int id) {
        contactRepository.deleteContact(id);
    }

    @Override
    public Contact findContactById(int id) {
        Contact contact = contactRepository.findContactById(id);
        if(contact == null){
            throw new NotFoundContactException(id);
        }
        return contact;
    }

    @Override
    public List<Contact> findAllContacts() {
        List<Contact> contacts = contactRepository.findAllContacts();
        if (contacts == null || contacts.isEmpty()){
            throw new NotFoundAnyContactException();
        }
        return contacts;
    }
}
