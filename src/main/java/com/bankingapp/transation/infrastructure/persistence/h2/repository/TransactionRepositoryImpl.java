package com.bankingapp.transation.infrastructure.persistence.h2.repository;

import com.bankingapp.transation.domain.model.Transaction;
import com.bankingapp.transation.domain.repository.TransactionRepository;
import com.bankingapp.transation.infrastructure.persistence.h2.dao.TransactionEntityDAO;
import com.bankingapp.transation.infrastructure.persistence.h2.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    DateTimeFormatter INSTANT_FORMATTER = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.from(ZoneOffset.UTC));

    @Autowired
    private TransactionEntityDAO transactionEntityDAO;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        try {
            TransactionEntity entity =
                    new TransactionEntity(transaction.getId().toString(),
                            transaction.getOrigin(),
                            transaction.getDestiny(),
                            transaction.getAmount().toString(),
                            transaction.getTimestamp().toString());
            transactionEntityDAO.save(entity);
            return transaction;
        } catch (Exception e) {
            //LOG message using Log4J
            throw e;
        }
    }

    @Override
    public List<Transaction> findTransactionByOriginAccount(String origin) {
        try {
            return transactionEntityDAO.findByOrigin(origin).stream()
                    .map(entity -> {
                        UUID id = UUID.fromString(entity.getId());
                        Instant timestamp =  Instant.from(INSTANT_FORMATTER.parse(entity.getTimestamp()));
                        BigDecimal amount = new BigDecimal(entity.getAmount());
                        return new Transaction(id, entity.getOrigin(), entity.getDestiny(), amount, timestamp);
                    }).toList();
        } catch (Exception e) {
            //LOG message using Log4J
            throw e;
        }
    }
}
