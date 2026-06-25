# Java Swing Desktop Banking Management System

## How to Run

From the project root:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
java -cp out Main
```

Account data is saved automatically in `data/accounts.dat`.

## Project Folder Structure

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

## Implemented Features

- Account creation with savings/current account selection.
- Secure login with 3-attempt lockout.
- Deposit, withdrawal, and fund transfer.
- Balance inquiry from the dashboard.
- Transaction history with `JTable`.
- Mini statement showing the last 5 transactions.
- Account details page.
- PIN change with current PIN verification.
- Account benefits page demonstrating polymorphism.
- File persistence using Java object serialization.
- Validation for empty fields, numeric amounts, PIN rules, insufficient balance, invalid account numbers, and failed login attempts.

## OOP Explanation

### Encapsulation

`Account.java` keeps important fields private:

- `accountNumber`
- `accountHolderName`
- `balance`
- `pin`

Other classes access account data through public methods such as `getAccountNumber()`, `getBalance()`, `verifyPin()`, `deposit()`, `withdraw()`, and `changePin()`.

### Inheritance

`SavingsAccount` and `CurrentAccount` both extend the abstract `Account` class. Common account state and behavior are inherited from `Account`, while each child class provides its own account-specific behavior.

### Polymorphism

The dashboard opens `BenefitsPage`, which calls:

```java
account.calculateBenefits()
```

The actual method that runs depends on the object type:

- `SavingsAccount` calculates yearly interest.
- `CurrentAccount` displays overdraft facility information.

### Abstraction

`Account` is an abstract class and defines abstract methods:

```java
public abstract String calculateBenefits();
public abstract String displayAccountType();
```

This hides account-specific implementation details behind a common account contract.

## File Storage Explanation

The `FileManager` class stores all accounts in `data/accounts.dat` using `ObjectOutputStream`. Each `Account` contains its own list of `Transaction` objects, so transaction history is saved together with the account.

When the program starts, `BankingService` calls `FileManager.loadAccounts()`. Whenever an account is created, money is deposited, withdrawn, transferred, a PIN is changed, or an account is locked, `BankingService` saves the updated account list automatically.

## Interview Questions and Answers

**Q1. Where is encapsulation used in this project?**  
Encapsulation is used in `Account.java`, where account number, holder name, balance, PIN, lock status, and transactions are private. Access is controlled through public methods.

**Q2. Why is `Account` abstract?**  
`Account` represents a general banking account. Since every real account must be a specific type, such as savings or current, it is abstract and requires child classes to implement benefit and account-type behavior.

**Q3. How is polymorphism demonstrated?**  
The GUI calls `calculateBenefits()` on an `Account` reference. Java dynamically runs the `SavingsAccount` or `CurrentAccount` version depending on the actual object.

**Q4. How does the app persist data?**  
The app serializes an `ArrayList<Account>` to `data/accounts.dat`. The file is loaded on startup and saved after every important change.

**Q5. Why use `ArrayList`?**  
`ArrayList` makes it easy to store, search, add, and iterate through accounts and transactions dynamically.

**Q6. How are failed login attempts handled?**  
`LoginPage` tracks failed attempts for the entered account. After 3 wrong PIN entries, `BankingService.lockAccount()` locks the account and saves the lock status.

**Q7. How is fund transfer implemented?**  
`BankingService.transfer()` finds the receiver account, verifies the sender has enough balance, withdraws from the sender, deposits to the receiver, and records transactions for both accounts.

**Q8. Which Java Date and Time API is used?**  
`LocalDate` stores account creation dates, and `LocalDateTime` stores transaction timestamps.

**Q9. What validations are included?**  
The app validates empty fields, numeric amount input, positive amounts, PIN format, PIN confirmation, receiver account existence, sufficient balance, invalid login, and locked accounts.

**Q10. How could this project be improved further?**  
Possible improvements include password hashing, database storage with JDBC, admin reporting, account unlock workflow, printable statements, and unit tests.
