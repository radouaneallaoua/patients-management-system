package com.allaoua.authservice.web;

import com.allaoua.authservice.dtos.AccountRequestDto;
import com.allaoua.authservice.dtos.AuthenticationRequestDto;
import com.allaoua.authservice.entities.Account;
import com.allaoua.authservice.enums.AppRole;
import com.allaoua.authservice.service.AuthInterface;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/auth")
public class AuthRestController {
    final private AuthInterface authInterface;

    public AuthRestController(AuthInterface authInterface) {
        this.authInterface = authInterface;
    }

    @PostMapping("/login")
    public Object login(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
        return authInterface.login(authenticationRequestDto);
    }

    @PostMapping("/register")
    public Account register(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return authInterface.saveAccount(accountRequestDto);
    }

    @PostMapping("/register/ms")
    public Account registerFromMs(@Valid @RequestBody AccountRequestDto accountRequestDto) {
        return authInterface.saveAccount(accountRequestDto);
    }

    @GetMapping("/roles")
    public List<AppRole> getRoles() {
        return authInterface.getRoles();
    }







}
