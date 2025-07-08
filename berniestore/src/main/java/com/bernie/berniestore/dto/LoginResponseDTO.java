package com.bernie.berniestore.dto;

public record LoginResponseDTO(String message, UserDTO user, String jwtToken) {
}
