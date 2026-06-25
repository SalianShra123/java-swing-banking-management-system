package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ChangePinPage extends JFrame {
    private final BankingService bankingService;
    private final Account account;
    private final JPasswordField currentPinField;
    private final JPasswordField newPinField;
    private final JPasswordField confirmPinField;

    public ChangePinPage(BankingService bankingService, Account account) {
        this.bankingService = bankingService;
        this.account = account;
        GuiHelper.setupFrame(this, "SecureBank ATM - Change PIN");

        JPanel root = GuiHelper.page("Change PIN", "Update your security PIN");
        JPanel form = GuiHelper.formPanel();
        currentPinField = new JPasswordField(20);
        newPinField = new JPasswordField(20);
        confirmPinField = new JPasswordField(20);
        JButton backButton = GuiHelper.secondaryButton("Back");
        JButton changeButton = GuiHelper.successButton("Update PIN");

        form.add(GuiHelper.label("Current PIN"), GuiHelper.gbc(0, 0));
        form.add(currentPinField, GuiHelper.gbc(1, 0));
        form.add(GuiHelper.label("New PIN"), GuiHelper.gbc(0, 1));
        form.add(newPinField, GuiHelper.gbc(1, 1));
        form.add(GuiHelper.label("Confirm PIN"), GuiHelper.gbc(0, 2));
        form.add(confirmPinField, GuiHelper.gbc(1, 2));
        form.add(backButton, GuiHelper.gbc(0, 3));
        form.add(changeButton, GuiHelper.gbc(1, 3));

        root.add(form);
        add(root);

        changeButton.addActionListener(e -> changePin());
        backButton.addActionListener(e -> back());
    }

    private void changePin() {
        String currentPin = new String(currentPinField.getPassword());
        String newPin = new String(newPinField.getPassword());
        String confirmPin = new String(confirmPinField.getPassword());

        if (currentPin.isEmpty() || newPin.isEmpty() || confirmPin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        if (!account.verifyPin(currentPin)) {
            JOptionPane.showMessageDialog(this, "Current PIN is incorrect.");
            return;
        }
        if (!newPin.matches("\\d{4,6}")) {
            JOptionPane.showMessageDialog(this, "New PIN must be 4 to 6 digits.");
            return;
        }
        if (!newPin.equals(confirmPin)) {
            JOptionPane.showMessageDialog(this, "New PIN and Confirm PIN must match.");
            return;
        }

        bankingService.changePin(account, newPin);
        JOptionPane.showMessageDialog(this, "PIN updated successfully.");
        back();
    }

    private void back() {
        GuiHelper.refresh(this, new DashboardPage(bankingService, account));
    }
}
