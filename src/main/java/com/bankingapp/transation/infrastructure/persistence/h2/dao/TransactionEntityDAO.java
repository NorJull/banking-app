package com.bankingapp.transation.infrastructure.persistence.h2.dao;

import com.bankingapp.transation.infrastructure.persistence.h2.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionEntityDAO extends JpaRepository<TransactionEntity, String> {
    List<TransactionEntity> findByOrigin(String origin);
}
