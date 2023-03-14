package com.bankingapp.transation.domain.service;

import com.bankingapp.transation.domain.exception.DomainException;
import com.bankingapp.transation.domain.model.Account;
import com.bankingapp.transation.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {


    final private AccountRepository accountRepository;

    public AccountService(@Autowired AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String customerId, String balance) {
        try {
            if (new BigDecimal(balance).compareTo(BigDecimal.ZERO) == -1) {
                throw new DomainException("Balance cannot be negative.");
            }

            String number = UUID.randomUUID().toString();
            Account account = new Account(number, new BigDecimal(balance), customerId);
            return accountRepository.saveAccount(account);
        } catch (Exception e) {
            //LOG message using Log4J
            throw e;
        }
    }

    public List<Account> findAllAccounts(){
        return accountRepository.findAllAccounts();
    }
}
