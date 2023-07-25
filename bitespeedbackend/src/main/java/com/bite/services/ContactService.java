package com.bite.services;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bite.entity.Contact;
import com.bite.repository.ContactRepository;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Contact identifyContact(String email, String phoneNumber) {
        Contact primaryContact = contactRepository.findByEmailOrPhoneNumber(email, phoneNumber);

        if (primaryContact == null) {
            // If no existing contact is found, create a new primary contact
            primaryContact = new Contact();
            primaryContact.setEmail(email);
            primaryContact.setPhoneNumber(phoneNumber);
            primaryContact.setLinkPrecedence(LinkPrecedence.PRIMARY);
            primaryContact = contactRepository.save(primaryContact);
        }

        return primaryContact;
    }

    public List<Contact> findSecondaryContacts(Contact primaryContact) {
        return contactRepository.findByLinkedContact(primaryContact);
    }
}