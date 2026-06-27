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

<img width="922" height="688" alt="Screenshot 2026-06-27 143535" src="https://github.com/user-attachments/assets/f9cea83c-2ce8-444f-8c7d-09a444c0c4d4" />
<img width="925" height="686" alt="Screenshot 2026-06-27 143608" src="https://github.com/user-attachments/assets/93b55ec4-f4b8-44b4-a54c-68325902a26c" />
<img width="921" height="690" alt="Screenshot 2026-06-27 143636" src="https://github.com/user-attachments/assets/d5acd4f2-c03b-4f56-adb3-0b35501f54d8" />
<img width="927" height="687" alt="Screenshot 2026-06-27 143705" src="https://github.com/user-attachments/assets/7ffcfd30-e9e9-44c3-be5e-29b2ebf19ffa" />
<img width="928" height="690" alt="Screenshot 2026-06-27 143751" src="https://github.com/user-attachments/assets/692288f3-80de-4c98-8b8d-8087f73730e9" />
<img width="927" height="687" alt="Screenshot 2026-06-27 143807" src="https://github.com/user-attachments/assets/9b9f56c7-7863-455f-9544-6cb960cd1a05" />
<img width="923" height="682" alt="Screenshot 2026-06-27 143818" src="https://github.com/user-attachments/assets/870d4be6-637f-4a7b-9118-d08b90e49bf5" />
<img width="926" height="688" alt="Screenshot 2026-06-27 143831" src="https://github.com/user-attachments/assets/c899d0b6-bd83-4516-bd68-1f583d4acf07" />
<img width="923" height="687" alt="Screenshot 2026-06-27 143845" src="https://github.com/user-attachments/assets/4a841213-8fc7-4616-932f-0c567c9249c4" />
<img width="923" height="692" alt="Screenshot 2026-06-27 143855" src="https://github.com/user-attachments/assets/05246a37-377a-4ce4-bde8-090c0f262032" />
<img width="922" height="687" alt="Screenshot 2026-06-27 143905" src="https://github.com/user-attachments/assets/fdc113c5-ea28-4433-b97e-25986a5f3899" />
<img width="928" height="686" alt="Screenshot 2026-06-27 143923" src="https://github.com/user-attachments/assets/8caedb96-f6d3-496d-af6c-58b0cc4f7641" />


