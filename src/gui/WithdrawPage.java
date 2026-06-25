package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WithdrawPage extends JFrame {
    private final BankingService bankingService;
    private final Account account;
    private final JTextField amountField;

    public WithdrawPage(BankingService bankingService, Account account) {
        this.bankingService = bankingService;
        this.account = account;
        GuiHelper.setupFrame(this, "SecureBank ATM - Withdraw");

        JPanel root = GuiHelper.page("Withdraw Money", "Withdraw available funds securely");
        JPanel form = GuiHelper.formPanel();
        amountField = new JTextField(20);
        JButton backButton = GuiHelper.secondaryButton("Back");
        JButton withdrawButton = GuiHelper.successButton("Withdraw");

        form.add(GuiHelper.label("Amount"), GuiHelper.gbc(0, 0));
        form.add(amountField, GuiHelper.gbc(1, 0));
        form.add(backButton, GuiHelper.gbc(0, 1));
        form.add(withdrawButton, GuiHelper.gbc(1, 1));

        root.add(form);
        add(root);

        withdrawButton.addActionListener(e -> withdraw());
        backButton.addActionListener(e -> back());
    }

    private void withdraw() {
        Double amount = parseAmount(amountField.getText());
        if (amount == null) {
            return;
        }
        if (!bankingService.withdraw(account, amount)) {
            JOptionPane.showMessageDialog(this, "Insufficient balance.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Withdrawal successful.");
        back();
    }

    private Double parseAmount(String value) {
        try {
            double amount = Double.parseDouble(value.trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than zero.");
                return null;
            }
            return amount;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Amount must be numeric.");
            return null;
        }
    }

    private void back() {
        GuiHelper.refresh(this, new DashboardPage(bankingService, account));
    }
}
