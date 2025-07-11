package com.bernie.berniestore.service;

import com.bernie.berniestore.dto.ContactRequestDTO;
import com.bernie.berniestore.dto.ContactResponseDTO;
import java.util.List;

public interface IContactService {

    boolean saveContact(ContactRequestDTO contactRequestDTO);

    List<ContactResponseDTO> getAllOpenMessages();

    void updateMessageStatus(Long contactId, String status);

}
