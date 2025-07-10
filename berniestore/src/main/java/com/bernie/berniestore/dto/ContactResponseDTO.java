package com.bernie.berniestore.dto;

public record ContactResponseDTO(Long contactId, String name, String email, String mobileNumber, String message,
                                 String status) {
}
