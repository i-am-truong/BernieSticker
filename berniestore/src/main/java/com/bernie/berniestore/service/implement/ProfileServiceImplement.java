package com.bernie.berniestore.service.implement;

import com.bernie.berniestore.dto.AddressDTO;
import com.bernie.berniestore.dto.ProfileRequestDTO;
import com.bernie.berniestore.dto.ProfileResponseDTO;
import com.bernie.berniestore.entity.Address;
import com.bernie.berniestore.entity.Customer;
import com.bernie.berniestore.repository.CustomerRepository;
import com.bernie.berniestore.service.IProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImplement implements IProfileService {

    private final CustomerRepository customerRepository;

    @Override
    public ProfileResponseDTO getProfile() {
        Customer customer = getAuthenticatedCustomer();
        return mapCustomerToProfileResponseDTO(customer);
    }

    @Override
    public ProfileResponseDTO updateProfile(ProfileRequestDTO profileRequestDTO) {
        Customer customer = getAuthenticatedCustomer();
        boolean isEmailUpdated = !customer.getEmail().equalsIgnoreCase(profileRequestDTO.getEmail().trim());
        BeanUtils.copyProperties(profileRequestDTO, customer);
        Address address = customer.getAddress();
        if (address == null) {
            address = new Address();
            address.setCustomer(customer);
        }
        address.setStreet(profileRequestDTO.getStreet());
        address.setCity(profileRequestDTO.getCity());
        address.setState(profileRequestDTO.getState());
        address.setPostalCode(profileRequestDTO.getPostalCode());
        address.setCountry(profileRequestDTO.getCountry());
        customer.setAddress(address);
        customer = customerRepository.save(customer);
        ProfileResponseDTO profileResponseDTO = mapCustomerToProfileResponseDTO(customer);
        profileResponseDTO.setEmailUpdated(isEmailUpdated);
        return profileResponseDTO;
    }

    private ProfileResponseDTO mapCustomerToProfileResponseDTO(Customer customer) {
        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();
        BeanUtils.copyProperties(customer, profileResponseDTO);
        if(customer.getAddress() != null){
            AddressDTO addressDTO = new AddressDTO();
            BeanUtils.copyProperties(customer.getAddress(), addressDTO);
            profileResponseDTO.setAddress(addressDTO);

        }
        return profileResponseDTO;
    }

    public Customer getAuthenticatedCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
