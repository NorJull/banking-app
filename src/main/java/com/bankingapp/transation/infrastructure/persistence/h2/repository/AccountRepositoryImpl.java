package com.bankingapp.transation.infrastructure.persistence.h2.repository;

import com.bankingapp.transation.domain.model.Account;
import com.bankingapp.transation.domain.repository.AccountRepository;
import com.bankingapp.transation.infrastructure.persistence.h2.dao.AccountEntityDAO;
import com.bankingapp.transation.infrastructure.persistence.h2.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @Autowired
    private AccountEntityDAO accountEntityDAO;

    @Override
    public Account saveAccount(Account account) {
        try {
            AccountEntity entity =
                    new AccountEntity(account.getNumber().toString(), account.getBalance().toString(), account.getCustomerId());
            accountEntityDAO.save(entity);
            return account;
        } catch (Exception e) {
            //LOG message using Log4J
            throw e;
        }
    }

    @Override
    public void updateAccountBalance(String number, BigDecimal balance) {
        try {
            Optional<Account> a = accountEntityDAO
                    .findById(number)
                    .map(accountEntity -> {
                        accountEntity.setBalance(balance.toString());
                        accountEntityDAO.save(accountEntity);
                        return new Account(accountEntity.getNumber(), new BigDecimal(accountEntity.getBalance()), accountEntity.getCustomerId());
                    });

        } catch (Exception e) {
            //LOG message using Log4J
            throw e;
        }
    }

    @Override
    public Optional<Account> findByNumber(String number) {
        try {
            return accountEntityDAO
                    .findById(number)
                    .map(entity -> {
                        return new Account(entity.getNumber(), new BigDecimal(entity.getBalance()), entity.getCustomerId());
                    });
        } catch (Exception e) {
            //LOG message using Log4J
            throw e;
        }
    }

    @Override
    public List<Account> findAllAccounts() {
        try {
            return accountEntityDAO
                    .findAll()
                    .stream()
                    .map(entity -> {
                        return new Account(entity.getNumber(), new BigDecimal(entity.getBalance()), entity.getCustomerId());
                    }).toList();
        } catch (Exception e) {
            //LOG message using Log4J
            throw e;
        }
    }
}
