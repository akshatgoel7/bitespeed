package com.bite.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bite.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByEmailOrPhoneNumber(String email, String phoneNumber);
    List<Contact> findByLinkedContact(Contact linkedContact);
}