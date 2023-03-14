package com.bankingapp.transation.infrastructure.persistence.h2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    private String id;

    private String origin;

    private String destiny;

    private String amount;

    private String timestamp;

    public TransactionEntity(String id, String origin, String destiny, String amount, String timestamp) {
        this.id = id;
        this.origin = origin;
        this.destiny = destiny;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
