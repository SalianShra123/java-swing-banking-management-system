package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DepositPage extends JFrame {
    private final BankingService bankingService;
    private final Account account;
    private final JTextField amountField;

    public DepositPage(BankingService bankingService, Account account) {
        this.bankingService = bankingService;
        this.account = account;
        GuiHelper.setupFrame(this, "SecureBank ATM - Deposit");

        JPanel root = GuiHelper.page("Deposit Money", "Add funds to your account");
        JPanel form = GuiHelper.formPanel();
        amountField = new JTextField(20);
        JButton backButton = GuiHelper.secondaryButton("Back");
        JButton depositButton = GuiHelper.successButton("Deposit");

        form.add(GuiHelper.label("Amount"), GuiHelper.gbc(0, 0));
        form.add(amountField, GuiHelper.gbc(1, 0));
        form.add(backButton, GuiHelper.gbc(0, 1));
        form.add(depositButton, GuiHelper.gbc(1, 1));

        root.add(form);
        add(root);

        depositButton.addActionListener(e -> deposit());
        backButton.addActionListener(e -> back());
    }

    private void deposit() {
        Double amount = parseAmount(amountField.getText());
        if (amount == null) {
            return;
        }
        bankingService.deposit(account, amount);
        JOptionPane.showMessageDialog(this, "Deposit successful.");
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
