package com.bankingapp.transation.application.rest.account;

import com.bankingapp.transation.domain.model.Account;

public class AccountTransformer {

    public static AccountDTO accountToAccountDTO(Account account){
        return new AccountDTO(account.getNumber().toString(),
                account.getBalance().toString(),
                account.getCustomerId());
    }
}
