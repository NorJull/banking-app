package com.bankingapp.transation.domain.service;

import com.bankingapp.transation.domain.exception.DomainException;
import com.bankingapp.transation.domain.model.Account;
import com.bankingapp.transation.domain.repository.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {


    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp(){
        accountService = new AccountService(accountRepository);
    }

    @Test
    void shouldCreateAccountWhenValidParameters(){
        //given
        String customerId = "1234556";
        BigDecimal balance = new BigDecimal("2000");
        String number = "12ce5254-fe8d-444c-ba47-3a85cbe2789b";

        Account account = new Account(number, balance,customerId);

        Mockito.when(accountRepository.saveAccount(Mockito.any())).thenReturn(account);

        //when
        Account savedAccount = accountService.createAccount(customerId, balance.toString());

        //then
        Assertions.assertEquals(account.getNumber(), savedAccount.getNumber());
        Assertions.assertEquals(account.getCustomerId(), savedAccount.getCustomerId());
        Assertions.assertEquals(account.getBalance(), savedAccount.getBalance());

    }

    @Test
    void shouldThrowAnExceptionWhenBalanceIsNegative(){
        //given
        String customerId = "1234556";
        BigDecimal balance = new BigDecimal("-2000");


        //when-then
        Assertions.assertThrows(DomainException.class, () -> {
            Account savedAccount = accountService.createAccount(customerId, balance.toString());
        });

    }


}
