package com.bernie.berniestore.repository;

import com.bernie.berniestore.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
