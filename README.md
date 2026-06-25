# Java Swing Desktop Banking Management System

A complete Java Swing desktop banking application and ATM simulation built with object-oriented programming concepts.

## Features

- Create savings or current accounts
- Login with account number and PIN
- Lock account after 3 failed login attempts
- Deposit money
- Withdraw money
- Transfer funds between accounts
- View full transaction history
- View mini statement with last 5 transactions
- View account details
- Change PIN
- Calculate account benefits
- Save account and transaction data using file handling

## Technologies Used

- Java
- Java Swing
- OOP concepts
- ArrayList
- File handling
- Exception handling
- Java Date and Time API

## OOP Concepts Demonstrated

- **Encapsulation:** Account fields like account number, holder name, balance, and PIN are private.
- **Inheritance:** `SavingsAccount` and `CurrentAccount` inherit from `Account`.
- **Polymorphism:** `calculateBenefits()` behaves differently for savings and current accounts.
- **Abstraction:** `Account` is an abstract class with abstract methods.

## Project Structure

```text
src/
├── Main.java
├── model/
│   ├── Account.java
│   ├── SavingsAccount.java
│   ├── CurrentAccount.java
│   └── Transaction.java
├── service/
│   └── BankingService.java
├── gui/
│   ├── LoginPage.java
│   ├── RegisterPage.java
│   ├── DashboardPage.java
│   ├── DepositPage.java
│   ├── WithdrawPage.java
│   ├── TransferPage.java
│   ├── TransactionHistoryPage.java
│   ├── MiniStatementPage.java
│   ├── AccountDetailsPage.java
│   ├── ChangePinPage.java
│   ├── BenefitsPage.java
│   └── GuiHelper.java
└── util/
    └── FileManager.java
```

## How to Compile and Run

Open PowerShell in the project folder and run:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
java -cp out Main
```

## Data Storage

The application stores account and transaction data in `data/accounts.dat`. This file is generated automatically when the application runs.
