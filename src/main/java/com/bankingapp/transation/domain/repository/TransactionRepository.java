package com.bankingapp.transation.domain.repository;

import com.bankingapp.transation.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> findTransactionByOriginAccount(String origin);

}
