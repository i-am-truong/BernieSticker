package com.bernie.berniestore.controller;

import com.bernie.berniestore.dto.ProfileRequestDTO;
import com.bernie.berniestore.dto.ProfileResponseDTO;
import com.bernie.berniestore.service.implement.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final IProfileService iProfileService;

    @GetMapping
    public ResponseEntity<ProfileResponseDTO> getProfile() {
        ProfileResponseDTO profileResponseDTO = iProfileService.getProfile();
        return ResponseEntity.ok(profileResponseDTO);
    }

    @PutMapping
    public ResponseEntity<ProfileResponseDTO> updateProfile(@Validated @RequestBody ProfileRequestDTO profileRequestDTO) {
        ProfileResponseDTO responseDTO = iProfileService.updateProfile(profileRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}
