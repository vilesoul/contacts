package com.br.contacts.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.br.contacts.service",
"com.br.contacts.repository"})
public class ContactsConfig {
}
