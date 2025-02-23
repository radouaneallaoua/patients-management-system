package com.allaoua.authservice.service;

import com.allaoua.authservice.dtos.AccountRequestDto;
import com.allaoua.authservice.dtos.AuthenticationRequestDto;
import com.allaoua.authservice.entities.Account;
import com.allaoua.authservice.enums.AppRole;
import com.allaoua.authservice.exception.EmailAlreadyExistsException;
import com.allaoua.authservice.repository.AccountRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class AuthInterfaceImpl implements  AuthInterface {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    public AuthInterfaceImpl(AccountRepository accountRepository, AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<AppRole> getRoles() {
        return List.of(AppRole.values());
    }


    @Override
    public Account saveAccount(AccountRequestDto accountRequestDto) {
        String email = accountRequestDto.getEmail();
        if(accountRepository.findByEmail(email) != null) {
            throw  new EmailAlreadyExistsException("email already exists");
        }
        Account account =new Account();
        account.setEmail(accountRequestDto.getEmail());
        account.setPassword(passwordEncoder.encode(accountRequestDto.getPassword()));
        account.setRole(getRoleEnum(accountRequestDto.getRole()));
        return accountRepository.save(account);
    }


    @Override
    public Map<String,String> login(AuthenticationRequestDto authenticationRequestDto) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(), authenticationRequestDto.getPassword()));
        Instant now = Instant.now();
        String userRole=authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        JwtClaimsSet jwtClaimsSet= JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                .subject(authenticationRequestDto.getEmail())
                .claim("role",userRole)
                .build();
        JwtEncoderParameters jwtEncoderParameters=JwtEncoderParameters.from(
                JwsHeader.with(
                        MacAlgorithm.HS512
                ).build(),
                jwtClaimsSet);
        String jwt=jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return  Map.of("access_token",jwt);
    }

    private  AppRole getRoleEnum(String role) {
        if("admin".equals(role)) {
            return AppRole.ADMIN;
        }
        if("patient".equals(role)) {
            return AppRole.PATIENT;
        }
        if("nurse".equals(role)) {
            return AppRole.NURSE;
        }
        return AppRole.DOCTOR;
    }
}
