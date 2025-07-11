package com.bernie.berniestore.controller;

import com.bernie.berniestore.dto.ContactInfoDTO;
import com.bernie.berniestore.dto.ContactRequestDTO;
import com.bernie.berniestore.service.IContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactService iContactService;
    private final ContactInfoDTO contactInfoDTO;

    @PostMapping
    public ResponseEntity<String> saveContact (@Valid @RequestBody ContactRequestDTO contactRequestDTO) {
        iContactService.saveContact(contactRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Request processed successfully!");
    }

    @GetMapping
    public ResponseEntity<ContactInfoDTO> getContactInfo() {
        return ResponseEntity.ok(contactInfoDTO);
    }
}
