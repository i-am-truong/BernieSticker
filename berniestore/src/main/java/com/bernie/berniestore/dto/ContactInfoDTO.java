package com.bernie.berniestore.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("contact")
public record ContactInfoDTO(String phone, String email, String address) {
}
