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
            Contact contact = transformToEntity(contactRequestDTO);
            contactRepository.save(contact);
            return true;
    }

    private Contact transformToEntity(ContactRequestDTO contactRequestDTO) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDTO, contact);
        return contact;
    }
}
