package com.butyter.contact.core.service;

import java.util.List;

import com.butyter.contact.core.domain.model.ContactDTO;

public interface ContactService {
    List<ContactDTO> getAllContacts();

    ContactDTO getContactById(int id);

    void deleteContact(int id);

    void createContact(ContactDTO contact);

    void updateContact(ContactDTO contact);
}
