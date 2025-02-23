package com.allaoua.authservice.service;

import com.allaoua.authservice.dtos.AccountRequestDto;
import com.allaoua.authservice.dtos.AuthenticationRequestDto;
import com.allaoua.authservice.entities.Account;
import com.allaoua.authservice.enums.AppRole;

import java.util.List;
import java.util.Map;

public interface AuthInterface {
    List<AppRole> getRoles();
    Account saveAccount(AccountRequestDto accountRequestDto);
    Map<String,String> login(AuthenticationRequestDto authenticationRequestDto);
}
