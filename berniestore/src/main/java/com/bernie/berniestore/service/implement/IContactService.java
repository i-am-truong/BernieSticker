package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.ContactRequestDTO;

public interface IContactService {

    boolean saveContact(ContactRequestDTO contactRequestDTO);
}
