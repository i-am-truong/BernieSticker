package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.ContactRequestDTO;
import com.bernie.berniestore.entity.Contact;
import com.bernie.berniestore.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactServiceImplement implements IContactService {

    private final ContactRepository contactRepository;


    @Override
    public boolean saveContact(ContactRequestDTO contactRequestDTO) {
        try {
            Contact contact = transformToEntity(contactRequestDTO);
            contact.setCreatedAt(Instant.now());
            contact.setCreatedBy(contactRequestDTO.getName());
            contactRepository.save(contact);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Contact transformToEntity(ContactRequestDTO contactRequestDTO) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDTO, contact);
        return contact;
    }
}
