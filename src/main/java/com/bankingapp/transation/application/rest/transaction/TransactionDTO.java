package com.bankingapp.transation.application.rest.transaction;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class TransactionDTO {

    private String id;

    @NotBlank(message = "Origin is mandatory")
    private String origin;

    @NotBlank(message = "Destiny is mandatory")
    private String destiny;

    @Positive
    @NotBlank(message = "Amount is mandatory")
    private String amount;

    private String timestamp;

    public TransactionDTO(String id, String origin, String destiny, String amount, String timestamp) {
        this.id = id;
        this.origin = origin;
        this.destiny = destiny;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public TransactionDTO() {
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
