package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Abstraction: Account is abstract, so the GUI/service works with a common contract.
public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    // Encapsulation: fields are private and can only be accessed through methods.
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private String pin;
    private final LocalDate creationDate;
    private boolean locked;
    private final ArrayList<Transaction> transactions;

    protected Account(String accountNumber, String accountHolderName, double balance, String pin) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = pin;
        this.creationDate = LocalDate.now();
        this.transactions = new ArrayList<>();
        this.transactions.add(new Transaction("Account Opened", balance, "Initial deposit"));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isLocked() {
        return locked;
    }

    public void lock() {
        locked = true;
    }

    public boolean verifyPin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public void changePin(String newPin) {
        pin = newPin;
    }

    public String getFormattedCreationDate() {
        return creationDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public void deposit(double amount, String description) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, description));
    }

    public void withdraw(double amount, String type, String description) {
        balance -= amount;
        transactions.add(new Transaction(type, amount, description));
    }

    public void receive(double amount, String description) {
        balance += amount;
        transactions.add(new Transaction("Transfer Received", amount, description));
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    // Polymorphism: child account types provide their own implementation.
    public abstract String calculateBenefits();

    // Abstraction: every concrete account must identify its type.
    public abstract String displayAccountType();
}
