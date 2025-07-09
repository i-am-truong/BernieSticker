package com.bernie.berniestore.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String mobileNumber;
    private String roles;
    private AddressDTO address;
}
