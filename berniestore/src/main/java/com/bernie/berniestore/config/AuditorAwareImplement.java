package com.bernie.berniestore.config;

import com.bernie.berniestore.entity.Customer;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAwareImplement")
public class AuditorAwareImplement implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            return Optional.of("Anonymous User");
        }
        Object principal = authentication.getPrincipal();
        String username;

        if(principal instanceof Customer customer) {
            username = customer.getEmail();
        } else {
            username = principal.toString();
        }

        return Optional.of(username);
    }
}
