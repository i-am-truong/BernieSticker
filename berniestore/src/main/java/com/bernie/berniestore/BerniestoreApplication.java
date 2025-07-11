package com.bernie.berniestore;

import com.bernie.berniestore.dto.ContactInfoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImplement")
@EnableConfigurationProperties(value =  {ContactInfoDTO.class})
public class BerniestoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BerniestoreApplication.class, args);
	}

}
