package com.bernie.berniestore.controller;

import com.bernie.berniestore.dto.LoginRequestDTO;
import com.bernie.berniestore.dto.LoginResponseDTO;
import com.bernie.berniestore.dto.RegisterRequestDTO;
import com.bernie.berniestore.dto.UserDTO;
import com.bernie.berniestore.entity.Customer;
import com.bernie.berniestore.repository.CustomerRepository;
import com.bernie.berniestore.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final CustomerRepository customerRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> apiLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password()));
            var userDTO = new UserDTO();
            var loggedInUser = (Customer) authentication.getPrincipal();
            BeanUtils.copyProperties(loggedInUser, userDTO);
            String jwtToken = jwtUtil.generateJwtToken(authentication);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(), userDTO, jwtToken));
        } catch (BadCredentialsException exception) {
            return builderErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        } catch (AuthenticationException exception) {
            return builderErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed");
        } catch (Exception exception) {
            return builderErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {

        CompromisedPasswordDecision decision = compromisedPasswordChecker.check(registerRequestDTO.getPassword());
        if(decision.isCompromised()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("password", "Choose a strong password"));
        }
        Optional<Customer> existingCustomer = customerRepository.findByEmailOrMobileNumber(
                registerRequestDTO.getEmail(), registerRequestDTO.getMobileNumber());
        if(existingCustomer.isPresent()){
            Map<String, String> errors = new HashMap<>();
            Customer customer = existingCustomer.get();

            if(customer.getEmail().equalsIgnoreCase(registerRequestDTO.getEmail())){
                errors.put("email", "Email is already registered");
            }
            if(customer.getMobileNumber().equalsIgnoreCase(registerRequestDTO.getMobileNumber())){
                errors.put("mobileNumber", "Mobile number is already registered");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(registerRequestDTO, customer);
        customer.setPasswordHash(passwordEncoder.encode(registerRequestDTO.getPassword()));
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    private ResponseEntity<LoginResponseDTO> builderErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(new LoginResponseDTO(message, null, null));
    }
}
