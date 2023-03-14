package com.bankingapp.transation.application.rest.transaction;

import com.bankingapp.transation.domain.model.Transaction;

public class TransactionTransformer {

    public static TransactionDTO transactionToTransactionDTO(Transaction transaction){
        return new TransactionDTO(transaction.getId().toString(),
                transaction.getOrigin(),
                transaction.getDestiny(),
                transaction.getAmount().toString(),
                transaction.getTimestamp().toString());
    }
}
