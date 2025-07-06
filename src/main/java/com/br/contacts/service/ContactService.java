package com.br.contacts.service;

import com.br.contacts.model.Contact;

import java.util.List;

public interface ContactService {

    void insertContact(Contact contact);

    Contact updateContact(int id, Contact contact);

    void deleteContact(int id);

    Contact findContactById(int id);

    List<Contact> findAllContacts();
}
