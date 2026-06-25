package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private final LocalDateTime dateTime;
    private final String type;
    private final double amount;
    private final String description;

    public Transaction(String type, double amount, String description) {
        this.dateTime = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public String getFormattedDateTime() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
