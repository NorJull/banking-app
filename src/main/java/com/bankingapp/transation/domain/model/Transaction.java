package com.bankingapp.transation.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Transaction {

    private UUID id;

    private String origin;

    private String destiny;

    private BigDecimal amount;

    private Instant timestamp;

    public Transaction(UUID  id, String origin, String destiny, BigDecimal amount, Instant timestamp) {
        this.id = id;
        this.origin = origin;
        this.destiny = destiny;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
