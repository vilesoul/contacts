package com.br.contacts;

import com.br.contacts.exception.NotFoundAnyContactException;
import com.br.contacts.exception.NotFoundContactException;
import com.br.contacts.model.Contact;
import com.br.contacts.repository.ContactRepository;
import com.br.contacts.service.ContactServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ContactsServiceUnitTests {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    @DisplayName("Testing the insertion of a contact")
    public void insertContactHappyFlow(){
        Contact contact = new Contact();
        contact.setName("test1");
        contact.setEmail("test1@test.com");
        contact.setPhone("111111");

        contactService.insertContact(contact);
        verify(contactRepository, times(1)).insertContact(contact);
    }


    @Test
    @DisplayName("Testing a contact update")
    public void testUpdateContactHappyFlow(){

        Contact contact =  new Contact();
        contact.setId(1);
        contact.setName("test1");
        contact.setEmail("test1@test.com");
        contact.setPhone("111111");

        when(contactRepository.updateContact(1,contact))
                .thenReturn(contact);

        Contact result = contactService.updateContact(1,contact);
        assertEquals("test1", result.getName());
    }


    @Test
    @DisplayName("Testing when a contact is not found")
    public void testUpdateContactNotFound(){

        Contact contact =  new Contact();
        contact.setId(2);
        contact.setName("maria");
        contact.setEmail("maria@test.com");
        contact.setPhone("22222");

        when(contactRepository.updateContact(2,contact))
                .thenReturn(null);

        assertThrows(NotFoundContactException.class, () -> {
            contactService.updateContact(2,contact);
        });

    }

    @Test
    @DisplayName("Deleting contact by id")
    public void testDeleteContactById() {
        int contactId = 42;

        contactService.deleteContact(contactId);

        verify(contactRepository, times(1)).deleteContact(contactId);
    }

    @Test
    @DisplayName("finding for contact by id")
    public void testFindingForContactById() {
        int contactId = 100;
        Contact mockContact =  new Contact();
        mockContact.setId(2);
        mockContact.setName("Ana");
        mockContact.setEmail("ana@test.com");
        mockContact.setPhone("56546563");

        when(contactRepository.findContactById(contactId)).thenReturn(mockContact);

        Contact result = contactService.findContactById(contactId);

        assertNotNull(result);
        assertEquals("Ana", result.getName());
    }

    @Test
    @DisplayName("should Throw Exception When Contact Not Found")
    public void shouldThrowExceptionWhenContactNotFound() {
        int contactId = 101;

        when(contactRepository.findContactById(contactId)).thenReturn(null);

        assertThrows(NotFoundContactException.class, () -> {
            contactService.findContactById(contactId);
        });
    }

    @Test
    @DisplayName("should Return List Of Contacts When Not Empty")
    public void shouldReturnListOfContactsWhenNotEmpty() {
        Contact mockContact =  new Contact();
        mockContact.setId(1);
        mockContact.setName("Ana");
        mockContact.setEmail("ana@test.com");
        mockContact.setPhone("56546563");

        Contact mockContact2 =  new Contact();
        mockContact2.setId(2);
        mockContact2.setName("Paula");
        mockContact2.setEmail("paula@test.com");
        mockContact2.setPhone("08982121");

        List<Contact> mockContacts = Arrays.asList(mockContact, mockContact2);

        when(contactRepository.findAllContacts()).thenReturn(mockContacts);

        List<Contact> result = contactService.findAllContacts();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Ana", result.get(0).getName());
    }

    @Test
    @DisplayName("should Throw Exception When Contacts List Is Empty")
    public void shouldThrowExceptionWhenContactsListIsEmpty() {
        when(contactRepository.findAllContacts()).thenReturn(List.of());

        assertThrows(NotFoundAnyContactException.class, () -> {
            contactService.findAllContacts();
        });
    }

    @Test
    @DisplayName("should Throw Exception When Contacts Is Null")
    public void shouldThrowExceptionWhenContactsIsNull() {
        when(contactRepository.findAllContacts()).thenReturn(null);

        assertThrows(NotFoundAnyContactException.class, () -> {
            contactService.findAllContacts();
        });
    }
}
