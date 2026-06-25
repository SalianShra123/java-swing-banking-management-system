package model;

// Inheritance: SavingsAccount inherits common account data and behavior from Account.
public class SavingsAccount extends Account {
    private static final long serialVersionUID = 1L;
    private static final double ANNUAL_INTEREST_RATE = 0.04;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, String pin) {
        super(accountNumber, accountHolderName, balance, pin);
    }

    // Polymorphism: savings accounts calculate interest as their benefit.
    @Override
    public String calculateBenefits() {
        double yearlyInterest = getBalance() * ANNUAL_INTEREST_RATE;
        return String.format("Savings Account Benefit:%nYearly interest at 4%% = Rs. %.2f%nProjected balance after one year = Rs. %.2f",
                yearlyInterest, getBalance() + yearlyInterest);
    }

    @Override
    public String displayAccountType() {
        return "Savings Account";
    }
}
