package com.allaoua.authservice.repository;

import com.allaoua.authservice.entities.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByEmail(String email);
}
