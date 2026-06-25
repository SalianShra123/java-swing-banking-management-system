package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class DashboardPage extends JFrame {
    private final BankingService bankingService;
    private final Account account;
    private final JLabel balanceLabel;

    public DashboardPage(BankingService bankingService, Account account) {
        this.bankingService = bankingService;
        this.account = account;
        GuiHelper.setupFrame(this, "SecureBank ATM - Dashboard");

        JPanel root = GuiHelper.page("Welcome, " + account.getAccountHolderName(), account.displayAccountType());
        balanceLabel = new JLabel("", JLabel.CENTER);
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        balanceLabel.setForeground(GuiHelper.GREEN);
        updateBalance();
        root.add(balanceLabel, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridLayout(3, 3, 12, 12));
        actions.setOpaque(false);

        addAction(actions, "Deposit", () -> GuiHelper.refresh(this, new DepositPage(bankingService, account)));
        addAction(actions, "Withdraw", () -> GuiHelper.refresh(this, new WithdrawPage(bankingService, account)));
        addAction(actions, "Transfer", () -> GuiHelper.refresh(this, new TransferPage(bankingService, account)));
        addAction(actions, "Transaction History", () -> GuiHelper.refresh(this, new TransactionHistoryPage(bankingService, account, false)));
        addAction(actions, "Mini Statement", () -> GuiHelper.refresh(this, new MiniStatementPage(bankingService, account)));
        addAction(actions, "Account Details", () -> GuiHelper.refresh(this, new AccountDetailsPage(bankingService, account)));
        addAction(actions, "Change PIN", () -> GuiHelper.refresh(this, new ChangePinPage(bankingService, account)));
        addAction(actions, "Calculate Benefits", () -> GuiHelper.refresh(this, new BenefitsPage(bankingService, account)));
        addAction(actions, "Logout", () -> GuiHelper.refresh(this, new LoginPage(bankingService)));

        root.add(actions, BorderLayout.SOUTH);
        add(root);
    }

    private void addAction(JPanel panel, String label, Runnable action) {
        JButton button = "Logout".equals(label) ? GuiHelper.secondaryButton(label) : GuiHelper.button(label);
        button.addActionListener(e -> action.run());
        panel.add(button);
    }

    private void updateBalance() {
        balanceLabel.setText(String.format("Current Balance: Rs. %.2f", account.getBalance()));
    }
}
