package com.bankingapp.transation.infrastructure.persistence.h2.dao;

import com.bankingapp.transation.infrastructure.persistence.h2.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountEntityDAO extends JpaRepository<AccountEntity, String> {

}
