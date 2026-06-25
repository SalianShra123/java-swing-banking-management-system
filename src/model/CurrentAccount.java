package model;

// Inheritance: CurrentAccount inherits common account data and behavior from Account.
public class CurrentAccount extends Account {
    private static final long serialVersionUID = 1L;
    private static final double OVERDRAFT_LIMIT = 25000.00;

    public CurrentAccount(String accountNumber, String accountHolderName, double balance, String pin) {
        super(accountNumber, accountHolderName, balance, pin);
    }

    // Polymorphism: current accounts describe overdraft support as their benefit.
    @Override
    public String calculateBenefits() {
        return String.format("Current Account Benefit:%nOverdraft facility available up to Rs. %.2f%nUseful for business cash-flow needs.",
                OVERDRAFT_LIMIT);
    }

    @Override
    public String displayAccountType() {
        return "Current Account";
    }
}
