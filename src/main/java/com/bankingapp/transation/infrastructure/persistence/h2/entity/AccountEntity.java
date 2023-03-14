package com.bankingapp.transation.infrastructure.persistence.h2.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private String number;

    private String balance;

    private String customerId;

    public AccountEntity(){}

    public AccountEntity(String number, String balance, String customerId) {
        this.number = number;
        this.balance = balance;
        this.customerId = customerId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
