package com.bankingapp.transation.domain.model;

import java.math.BigDecimal;

public class Account {

    private String number;

    private BigDecimal balance;

    private String customerId;

    public Account(String number, BigDecimal balance, String customerId) {
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
