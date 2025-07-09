package com.bernie.berniestore.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDTO {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
    private AddressDTO address;
    private boolean emailUpdated;
}
