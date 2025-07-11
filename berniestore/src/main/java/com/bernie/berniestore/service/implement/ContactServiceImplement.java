package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.constants.ApplicationConstants;
import com.bernie.berniestore.dto.ContactRequestDTO;
import com.bernie.berniestore.dto.ContactResponseDTO;
import com.bernie.berniestore.entity.Contact;
import com.bernie.berniestore.exception.ResourceNotFoundException;
import com.bernie.berniestore.repository.ContactRepository;
import com.bernie.berniestore.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ContactResponseDTO> getAllOpenMessages() {
        List<Contact> contacts = contactRepository.fetchByStatus(ApplicationConstants.OPEN_MESSAGE);
        return contacts.stream().map(this::mapToContactResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void updateMessageStatus(Long contactId, String status) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "Contact ID", contactId.toString()));
        contact.setStatus(status);
        contactRepository.save(contact);
    }

    private Contact transformToEntity(ContactRequestDTO contactRequestDTO) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDTO, contact);
        contact.setStatus(ApplicationConstants.OPEN_MESSAGE);
        return contact;
    }

    private ContactResponseDTO mapToContactResponseDTO(Contact contact ) {
        return new ContactResponseDTO(
                contact.getContactId(),
                contact.getName(),
                contact.getEmail(),
                contact.getMobileNumber(),
                contact.getMessage(),
                contact.getStatus()
        );
    }
}
