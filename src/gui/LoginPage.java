package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends JFrame {
    private final BankingService bankingService;
    private final JTextField accountField;
    private final JPasswordField pinField;
    private int failedAttempts;
    private String lastAttemptedAccount;

    public LoginPage(BankingService bankingService) {
        this.bankingService = bankingService;
        GuiHelper.setupFrame(this, "SecureBank ATM - Login");

        JPanel root = GuiHelper.page("SecureBank ATM", "Professional desktop banking management system");
        JPanel form = GuiHelper.formPanel();

        accountField = new JTextField(22);
        pinField = new JPasswordField(22);
        JButton loginButton = GuiHelper.successButton("Login");
        JButton createButton = GuiHelper.button("Create Account");

        form.add(GuiHelper.label("Account Number"), GuiHelper.gbc(0, 0));
        form.add(accountField, GuiHelper.gbc(1, 0));
        form.add(GuiHelper.label("PIN"), GuiHelper.gbc(0, 1));
        form.add(pinField, GuiHelper.gbc(1, 1));
        form.add(loginButton, GuiHelper.gbc(0, 2));
        form.add(createButton, GuiHelper.gbc(1, 2));

        root.add(form);
        add(root);

        loginButton.addActionListener(e -> login());
        createButton.addActionListener(e -> GuiHelper.refresh(this, new RegisterPage(bankingService)));
    }

    private void login() {
        String accountNumber = accountField.getText().trim();
        String pin = new String(pinField.getPassword());

        if (accountNumber.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter account number and PIN.");
            return;
        }

        Account account = bankingService.findAccount(accountNumber);
        if (account == null) {
            JOptionPane.showMessageDialog(this, "Invalid account number.");
            return;
        }
        if (account.isLocked()) {
            JOptionPane.showMessageDialog(this, "This account is locked due to failed login attempts.");
            return;
        }

        if (!accountNumber.equals(lastAttemptedAccount)) {
            failedAttempts = 0;
            lastAttemptedAccount = accountNumber;
        }

        if (account.verifyPin(pin)) {
            failedAttempts = 0;
            GuiHelper.refresh(this, new DashboardPage(bankingService, account));
            return;
        }

        failedAttempts++;
        if (failedAttempts >= 3) {
            bankingService.lockAccount(account);
            JOptionPane.showMessageDialog(this, "Three failed attempts. Account locked.");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect PIN. Attempts left: " + (3 - failedAttempts));
        }
    }
}
