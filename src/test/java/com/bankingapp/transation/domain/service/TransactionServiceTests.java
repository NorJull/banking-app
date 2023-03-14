package com.bankingapp.transation.domain.service;

import com.bankingapp.transation.domain.exception.DomainException;
import com.bankingapp.transation.domain.model.Account;
import com.bankingapp.transation.domain.model.Transaction;
import com.bankingapp.transation.domain.repository.AccountRepository;
import com.bankingapp.transation.domain.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {


    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService(accountRepository, transactionRepository);
    }

    @Test
    void shouldMakeTransactionWhenValidParameters() {

        //given
        String origin = "eb3a3b1c-a117-4b50-8452-312e7845cf93";
        String destiny = "89d51af3-a802-4b36-8480-c70961f843ae";
        BigDecimal amount = new BigDecimal(2000);
        String customerId = "1234556";

        Account originAccount = new Account(origin, new BigDecimal(5000), customerId);
        Account destinyAccount = new Account(destiny, new BigDecimal(2000), customerId);
        Transaction transaction = new Transaction(UUID.randomUUID(), origin, destiny, amount, Instant.now());

        Mockito.when(accountRepository.findByNumber(origin)).thenReturn(Optional.of(originAccount));
        Mockito.when(accountRepository.findByNumber(destiny)).thenReturn(Optional.of(destinyAccount));
        Mockito.when(transactionRepository.saveTransaction(Mockito.any())).thenReturn(transaction);
        //when
        Transaction savedTransaction = transactionService.makeATransaction(origin, destiny, amount).get();

        //then
        Mockito.verify(accountRepository, Mockito.times(2))
                .updateAccountBalance(Mockito.any(), Mockito.any());
        Assertions.assertEquals(origin, savedTransaction.getOrigin());
        Assertions.assertEquals(destiny, savedTransaction.getDestiny());
        Assertions.assertEquals(amount, savedTransaction.getAmount());
    }

    @Test
    void shouldThrowDomainExceptionWhenAmountIsNegative() {

        //given
        String origin = "eb3a3b1c-a117-4b50-8452-312e7845cf93";
        String destiny = "89d51af3-a802-4b36-8480-c70961f843ae";
        BigDecimal amount = new BigDecimal(-2000);

        //when-then
        Assertions.assertThrows(DomainException.class, () -> {
            transactionService.makeATransaction(origin, destiny, amount);
        });
    }

    @Test
    void shouldNotMakeTransactionWhenAmountIsGreaterThanOriginAccountBalance() {

        //given
        String origin = "eb3a3b1c-a117-4b50-8452-312e7845cf93";
        String destiny = "89d51af3-a802-4b36-8480-c70961f843ae";
        BigDecimal amount = new BigDecimal(2000);
        String customerId = "1234556";

        Account originAccount = new Account(origin, new BigDecimal(1000), customerId);


        Mockito.when(accountRepository.findByNumber(origin)).thenReturn(Optional.of(originAccount));

        //when
        Optional<Transaction> optionalTransaction = transactionService.makeATransaction(origin, destiny, amount);


        //then
        Assertions.assertTrue(optionalTransaction.isEmpty());
    }

    @Test
    void shouldNotMakeTransactionWhenOriginAccountDoesNotExist() {
        //given
        String origin = "eb3a3b1c-a117-4b50-8452-312e7845cf93";
        String destiny = "89d51af3-a802-4b36-8480-c70961f843ae";
        BigDecimal amount = new BigDecimal(2000);

        Mockito.when(accountRepository.findByNumber(origin)).thenReturn(Optional.empty());
        //when
        Optional<Transaction> optionalTransaction = transactionService.makeATransaction(origin, destiny, amount);

        //then
        Assertions.assertTrue(optionalTransaction.isEmpty());
    }

    @Test
    void shouldNotMakeTransactionWhenDestinyAccountDoesNotExist() {
        //given
        String origin = "eb3a3b1c-a117-4b50-8452-312e7845cf93";
        String destiny = "89d51af3-a802-4b36-8480-c70961f843ae";
        BigDecimal amount = new BigDecimal(2000);
        String customerId = "1234556";

        Account originAccount = new Account(origin, new BigDecimal(5000), customerId);
        Account destinyAccount = new Account(destiny, new BigDecimal(2000), customerId);

        Mockito.when(accountRepository.findByNumber(origin)).thenReturn(Optional.of(originAccount));
        Mockito.when(accountRepository.findByNumber(destiny)).thenReturn(Optional.empty());
        //when
        Optional<Transaction> optionalTransaction = transactionService.makeATransaction(origin, destiny, amount);

        //then
        Assertions.assertTrue(optionalTransaction.isEmpty());
    }
}
