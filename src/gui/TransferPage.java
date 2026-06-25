package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TransferPage extends JFrame {
    private final BankingService bankingService;
    private final Account account;
    private final JTextField receiverField;
    private final JTextField amountField;

    public TransferPage(BankingService bankingService, Account account) {
        this.bankingService = bankingService;
        this.account = account;
        GuiHelper.setupFrame(this, "SecureBank ATM - Transfer");

        JPanel root = GuiHelper.page("Fund Transfer", "Send money to another SecureBank account");
        JPanel form = GuiHelper.formPanel();
        receiverField = new JTextField(20);
        amountField = new JTextField(20);
        JButton backButton = GuiHelper.secondaryButton("Back");
        JButton transferButton = GuiHelper.successButton("Transfer");

        form.add(GuiHelper.label("Receiver Account Number"), GuiHelper.gbc(0, 0));
        form.add(receiverField, GuiHelper.gbc(1, 0));
        form.add(GuiHelper.label("Amount"), GuiHelper.gbc(0, 1));
        form.add(amountField, GuiHelper.gbc(1, 1));
        form.add(backButton, GuiHelper.gbc(0, 2));
        form.add(transferButton, GuiHelper.gbc(1, 2));

        root.add(form);
        add(root);

        transferButton.addActionListener(e -> transfer());
        backButton.addActionListener(e -> back());
    }

    private void transfer() {
        String receiverAccount = receiverField.getText().trim();
        if (receiverAccount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Receiver account number is required.");
            return;
        }
        if (bankingService.findAccount(receiverAccount) == null) {
            JOptionPane.showMessageDialog(this, "Receiver account not found.");
            return;
        }
        if (receiverAccount.equals(account.getAccountNumber())) {
            JOptionPane.showMessageDialog(this, "You cannot transfer to the same account.");
            return;
        }

        Double amount = parseAmount(amountField.getText());
        if (amount == null) {
            return;
        }
        if (!bankingService.transfer(account, receiverAccount, amount)) {
            JOptionPane.showMessageDialog(this, "Transfer failed. Check balance and receiver details.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Transfer successful.");
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
