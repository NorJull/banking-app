package com.bankingapp.transation.application.rest.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AccountDTO {

    private String number;

    @Positive
    @NotBlank(message = "Balance is mandatory")
    private String balance;

    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;

    public AccountDTO(String number, String balance, String customerId) {
        this.number = number;
        this.balance = balance;
        this.customerId = customerId;
    }

    public AccountDTO(){}

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
