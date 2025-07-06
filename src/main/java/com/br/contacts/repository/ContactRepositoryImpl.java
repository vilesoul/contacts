package com.br.contacts.repository;

import com.br.contacts.exception.NotFoundContactException;
import com.br.contacts.model.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbc;

    public ContactRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insertContact(Contact contact) {

        String sql = "INSERT INTO contact (name, email, phone) VALUES (?,?,?)";
        jdbc.update(sql,
                contact.getName(),
                contact.getEmail(),
                contact.getPhone());

    }

    @Override
    public Contact updateContact(int id, Contact contact) {

        Contact contactQuery = findContactById(id);

        if(contactQuery != null){

            String sql = "UPDATE contact SET name = ?, email = ?, phone = ?" +
                    "WHERE id = ? ";
            jdbc.update(sql,
                    contact.getName(),
                    contact.getEmail(),
                    contact.getPhone(),
                    contact.getId());

            contactQuery = findContactById(id);

            return contactQuery;

        } else{
            return null;
        }
    }

    @Override
    public void deleteContact(int id) {

        String sql =  "DELETE FROM contact WHERE id = ?";

        Contact contact = findContactById(id);

        if(contact!= null){
            jdbc.update(sql,id);
        }
        else {
            throw new NotFoundContactException(id);
        }
 }

    @Override
    public Contact findContactById(int id) {

        String sql = "SELECT * FROM contact WHERE id = ?";

        RowMapper<Contact> contactRowMapper = (r,i) -> {
            Contact rowObject = new Contact();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setEmail(r.getString("email"));
            rowObject.setPhone(r.getString("phone"));
            return rowObject;
        };

        return jdbc.queryForObject(sql, new Object[]{id}, contactRowMapper);
    }

    @Override
    public List<Contact> findAllContacts() {
        String sql = "SELECT * FROM contact";

        RowMapper<Contact> contactRowMapper = (r,i) -> {
            Contact rowObject = new Contact();
            rowObject.setId(r.getInt("id"));
            rowObject.setName(r.getString("name"));
            rowObject.setEmail(r.getString("email"));
            rowObject.setPhone(r.getString("phone"));
            return rowObject;
        };

        return jdbc.query(sql, contactRowMapper);
    }
}
