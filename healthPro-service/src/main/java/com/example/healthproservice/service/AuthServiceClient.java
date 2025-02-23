package com.example.healthproservice.service;

import com.example.healthproservice.model.auth.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${service.name.auth}", url = "${service.url.auth}")
public interface AuthServiceClient {
    @GetMapping("/account/{email}")
    Account getAccountById(@PathVariable("email") String email);
    @PostMapping("/auth/register")
    Account createAccount(@RequestBody Account account);
}
