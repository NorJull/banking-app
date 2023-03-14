package com.bankingapp.transation.domain.service;

import com.bankingapp.transation.domain.exception.DomainException;
import com.bankingapp.transation.domain.model.Transaction;
import com.bankingapp.transation.domain.repository.AccountRepository;
import com.bankingapp.transation.domain.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    final private AccountRepository accountRepository;


    final private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Optional<Transaction> makeATransaction(String origin, String destiny, BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) == -1) {
            throw new DomainException("Transaction amount cannot be negative.");
        }

        return accountRepository.findByNumber(origin)
                .filter(account -> validWithdrawAmount(account.getBalance(), amount))
                .flatMap(originAccount -> {
                    return accountRepository.findByNumber(destiny).map(destinyAccount -> {
                        updateOriginAccountBalance(originAccount.getNumber(), amount, originAccount.getBalance());
                        updateDestinyAccountBalance(destinyAccount.getNumber(), amount, destinyAccount.getBalance());
                        Transaction transaction = createTransaction(origin, destiny, amount);
                        return transactionRepository.saveTransaction(transaction);
                    });

                });
    }

    public List<Transaction> findTransactionByOriginAccount(String origin){
        return transactionRepository.findTransactionByOriginAccount(origin);
    }

    private void updateOriginAccountBalance(String accountNumber, BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = currentBalance.subtract(amount);
        accountRepository.updateAccountBalance(accountNumber, newBalance);
    }

    private void updateDestinyAccountBalance(String accountNumber, BigDecimal amount, BigDecimal currentBalance) {
        BigDecimal newBalance = currentBalance.add(amount);
        accountRepository.updateAccountBalance(accountNumber, newBalance);
    }

    private Boolean validWithdrawAmount(BigDecimal balance, BigDecimal amount) {
        return balance.compareTo(amount) >= 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    private Transaction createTransaction(String origin, String destiny, BigDecimal amount) {
        UUID transactionId = UUID.randomUUID();
        return new Transaction(transactionId, origin, destiny, amount, Instant.now());
    }
}
