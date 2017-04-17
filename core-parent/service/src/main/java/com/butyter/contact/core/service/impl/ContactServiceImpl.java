
package com.butyter.contact.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.butyter.contact.core.db.dao.ContactDAO;
import com.butyter.contact.core.domain.model.ContactDTO;
import com.butyter.contact.core.service.ContactService;
import com.butyter.contact.core.service.mapper.ContactMapper;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDAO contactDAO;
    
    @Autowired
    private ContactMapper contactMapper;

    @Transactional(readOnly = true)
    public List<ContactDTO> getAllContacts() {
        return contactDAO.getAllContacts().stream().map(e -> contactMapper.toDTO(e)).collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public ContactDTO getContactById(int id) {
        return contactMapper.toDTO(contactDAO.getContactById(id));
    }
    
    public void deleteContact(int id) {
        contactDAO.deleteContact(id);
    }

    public void createContact(ContactDTO contact) {
        contactDAO.createContact(contactMapper.fromDTO(contact));
    }

    public void updateContact(ContactDTO contact) {
        contactDAO.updateContact(contactMapper.fromDTO(contact));
    }

}