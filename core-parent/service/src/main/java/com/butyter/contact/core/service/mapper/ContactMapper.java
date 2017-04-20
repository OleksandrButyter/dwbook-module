
package com.butyter.contact.core.service.mapper;

import com.butyter.contact.core.db.model.Contact;
import com.butyter.contact.core.domain.model.ContactDTO;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ContactMapper implements Mapper<Contact, ContactDTO> {
   
    public ContactDTO toDTO(Contact entity) {
    	ContactDTO dto = null;
        if (entity != null) {
            dto = new ContactDTO();
            dto.setId(entity.getId());
            dto.setFirstName(entity.getFirstName());
            dto.setLastName(entity.getLastName());
            dto.setPhone(entity.getPhone());
        }
        return dto;
    }

    public Contact fromDTO(ContactDTO dto) {
    	Contact entity = new Contact();    	
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        return entity;
    }
}
