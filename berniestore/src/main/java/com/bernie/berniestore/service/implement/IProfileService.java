package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.ProfileRequestDTO;
import com.bernie.berniestore.dto.ProfileResponseDTO;

public interface IProfileService {

    ProfileResponseDTO getProfile();

    ProfileResponseDTO updateProfile(ProfileRequestDTO profileRequestDTO);
}
