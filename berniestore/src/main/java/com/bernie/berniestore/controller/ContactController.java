package com.bernie.berniestore.controller;

import com.bernie.berniestore.dto.ContactRequestDTO;
import com.bernie.berniestore.service.implement.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService icontactService;

    @PostMapping
    public String saveContact(@RequestBody ContactRequestDTO contactRequestDTO) {
        boolean isSaved = icontactService.saveContact(contactRequestDTO);
        if (isSaved) {
            return "Request processed successfully!";
        } else {
            return "An error occurred. Please try again or contact Dev team.";
        }
    }
}
