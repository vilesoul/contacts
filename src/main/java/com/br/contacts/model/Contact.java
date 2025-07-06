package com.br.contacts.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Contact {

    private int id;
    @NotBlank(message = "Name is required")
    private String name;
    @Email(message = "Invalid email")
    private String email;
    @Size(min = 10, max = 11, message = "Phone number must be between 10 and 11 digits long")
    private String phone;

    public static Contact of(String name, String email, String phone){
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setPhone(phone);

        return contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
