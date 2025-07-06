package com.br.contacts.repository;

import com.br.contacts.model.Contact;

import java.util.List;

public interface ContactRepository {

    void insertContact(Contact contact);
    Contact updateContact(int id, Contact contact);
    void deleteContact(int id);

    Contact findContactById(int id);

    List<Contact> findAllContacts();
}
