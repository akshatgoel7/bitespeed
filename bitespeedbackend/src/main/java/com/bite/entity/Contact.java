package com.bite.entity;


import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "linked_id")
    private Contact linkedContact;

    @Enumerated(EnumType.STRING)
    private LinkPrecedence linkPrecedence;

	public void setEmail(String email2) {
		// TODO Auto-generated method stub
		
	}

	public void setPhoneNumber(String phoneNumber2) {
		// TODO Auto-generated method stub
		
	}

    // Getters and setters
}

enum LinkPrecedence {
    PRIMARY, SECONDARY
}