package service;

import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;
import util.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankingService {
    private final ArrayList<Account> accounts;
    private final Random random;

    public BankingService() {
        accounts = FileManager.loadAccounts();
        random = new Random();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account createAccount(String holderName, String accountType, double initialDeposit, String pin) {
        String accountNumber = generateUniqueAccountNumber();
        Account account;
        if ("Current Account".equals(accountType)) {
            account = new CurrentAccount(accountNumber, holderName, initialDeposit, pin);
        } else {
            account = new SavingsAccount(accountNumber, holderName, initialDeposit, pin);
        }
        accounts.add(account);
        save();
        return account;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean deposit(Account account, double amount) {
        account.deposit(amount, "Cash deposit");
        save();
        return true;
    }

    public boolean withdraw(Account account, double amount) {
        if (account.getBalance() < amount) {
            return false;
        }
        account.withdraw(amount, "Withdrawal", "ATM withdrawal");
        save();
        return true;
    }

    public boolean transfer(Account sender, String receiverAccountNumber, double amount) {
        Account receiver = findAccount(receiverAccountNumber);
        if (receiver == null || receiver == sender || sender.getBalance() < amount) {
            return false;
        }

        sender.withdraw(amount, "Transfer Sent", "Sent to " + receiver.getAccountNumber());
        receiver.receive(amount, "Received from " + sender.getAccountNumber());
        save();
        return true;
    }

    public void changePin(Account account, String newPin) {
        account.changePin(newPin);
        save();
    }

    public void lockAccount(Account account) {
        account.lock();
        save();
    }

    public void save() {
        FileManager.saveAccounts(accounts);
    }

    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = "10" + (10000000 + random.nextInt(90000000));
        } while (findAccount(accountNumber) != null);
        return accountNumber;
    }
}
