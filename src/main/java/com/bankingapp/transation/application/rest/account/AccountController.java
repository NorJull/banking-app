package com.bankingapp.transation.application.rest.account;

import com.bankingapp.transation.domain.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/account")
@RestController
public class AccountController {


    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    private ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        try {
            return ResponseEntity.ok(AccountTransformer
                    .accountToAccountDTO(accountService.createAccount(accountDTO.getCustomerId(), accountDTO.getBalance())));
        } catch(Exception e) {
            //LOG message using Log4J
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping
    private ResponseEntity<List<AccountDTO>> findAllAccounts(){
        try {
            return ResponseEntity.ok(accountService.findAllAccounts().stream().map(AccountTransformer::accountToAccountDTO).toList());
        } catch(Exception e) {
            //LOG message using Log4J
            return ResponseEntity.internalServerError().build();
        }

    }
}
