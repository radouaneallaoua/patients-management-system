package _gi.projet_dev.patient_management.openFiegnClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AUTH-SERVICE")
public interface AccountClient {
    @PostMapping("/auth/register/ms")
    AccountDto registerAccount(@RequestBody Account account);



}
