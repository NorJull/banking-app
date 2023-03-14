package com.bankingapp.transation.domain.repository;

import com.bankingapp.transation.domain.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account saveAccount(Account account);

    void updateAccountBalance(String number, BigDecimal balance);

    Optional<Account> findByNumber(String number);

    List<Account> findAllAccounts();
}
