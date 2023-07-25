package com.bite.controller;


import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bite.entity.Contact;
import com.bite.services.ContactService;

@RestController
public class IdentityController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/identify")
    public ResponseEntity<Map<String, Object>> identifyContact(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String phoneNumber = request.get("phoneNumber");

        if (email == null && phoneNumber == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Email or phoneNumber is required."));
        }

        Contact primaryContact = contactService.identifyContact(email, phoneNumber);
        List<Contact> secondaryContacts = contactService.findSecondaryContacts(primaryContact);

        Map<String, Object> response = new HashMap<>();
        response.put("contact", primaryContact);
        response.put("secondaryContactIds", secondaryContacts.stream().map(Contact::getId).collect(Collectors.toList()));

        return ResponseEntity.ok(response);
    }
}