package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPage extends JFrame {
    private final BankingService bankingService;
    private final JTextField nameField;
    private final JComboBox<String> typeCombo;
    private final JTextField depositField;
    private final JPasswordField pinField;
    private final JPasswordField confirmPinField;

    public RegisterPage(BankingService bankingService) {
        this.bankingService = bankingService;
        GuiHelper.setupFrame(this, "SecureBank ATM - Create Account");

        JPanel root = GuiHelper.page("Create Account", "Open a savings or current account in seconds");
        JPanel form = GuiHelper.formPanel();

        nameField = new JTextField(24);
        typeCombo = new JComboBox<>(new String[]{"Savings Account", "Current Account"});
        depositField = new JTextField(24);
        pinField = new JPasswordField(24);
        confirmPinField = new JPasswordField(24);
        JButton createButton = GuiHelper.successButton("Create Account");
        JButton backButton = GuiHelper.secondaryButton("Back");

        form.add(GuiHelper.label("Full Name"), GuiHelper.gbc(0, 0));
        form.add(nameField, GuiHelper.gbc(1, 0));
        form.add(GuiHelper.label("Account Type"), GuiHelper.gbc(0, 1));
        form.add(typeCombo, GuiHelper.gbc(1, 1));
        form.add(GuiHelper.label("Initial Deposit"), GuiHelper.gbc(0, 2));
        form.add(depositField, GuiHelper.gbc(1, 2));
        form.add(GuiHelper.label("PIN"), GuiHelper.gbc(0, 3));
        form.add(pinField, GuiHelper.gbc(1, 3));
        form.add(GuiHelper.label("Confirm PIN"), GuiHelper.gbc(0, 4));
        form.add(confirmPinField, GuiHelper.gbc(1, 4));
        form.add(backButton, GuiHelper.gbc(0, 5));
        form.add(createButton, GuiHelper.gbc(1, 5));

        root.add(form);
        add(root);

        createButton.addActionListener(e -> createAccount());
        backButton.addActionListener(e -> GuiHelper.refresh(this, new LoginPage(bankingService)));
    }

    private void createAccount() {
        String name = nameField.getText().trim();
        String type = (String) typeCombo.getSelectedItem();
        String pin = new String(pinField.getPassword());
        String confirmPin = new String(confirmPinField.getPassword());

        if (name.isEmpty() || depositField.getText().trim().isEmpty() || pin.isEmpty() || confirmPin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        if (!pin.matches("\\d{4,6}")) {
            JOptionPane.showMessageDialog(this, "PIN must be 4 to 6 digits.");
            return;
        }
        if (!pin.equals(confirmPin)) {
            JOptionPane.showMessageDialog(this, "PIN and Confirm PIN must match.");
            return;
        }

        try {
            double initialDeposit = Double.parseDouble(depositField.getText().trim());
            if (initialDeposit < 0) {
                JOptionPane.showMessageDialog(this, "Initial deposit cannot be negative.");
                return;
            }
            Account account = bankingService.createAccount(name, type, initialDeposit, pin);
            JOptionPane.showMessageDialog(this, "Account created successfully.\nAccount Number: " + account.getAccountNumber());
            GuiHelper.refresh(this, new LoginPage(bankingService));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Initial deposit must be numeric.");
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
