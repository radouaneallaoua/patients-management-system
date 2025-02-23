package com.allaoua.authservice.service;

import com.allaoua.authservice.entities.Account;
import com.allaoua.authservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account=accountRepository.findByEmail(email);
        return  User.withUsername(account.getEmail()).password(account.getPassword()).authorities(new SimpleGrantedAuthority(account.getRole().toString())).build();
    }
}
