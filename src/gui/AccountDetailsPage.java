package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class AccountDetailsPage extends JFrame {
    public AccountDetailsPage(BankingService bankingService, Account account) {
        GuiHelper.setupFrame(this, "SecureBank ATM - Account Details");

        JPanel root = GuiHelper.page("Account Details", "Your account profile and status");
        JPanel form = GuiHelper.formPanel();

        addRow(form, "Account Holder Name", account.getAccountHolderName(), 0);
        addRow(form, "Account Number", account.getAccountNumber(), 1);
        addRow(form, "Account Type", account.displayAccountType(), 2);
        addRow(form, "Current Balance", String.format("Rs. %.2f", account.getBalance()), 3);
        addRow(form, "Account Creation Date", account.getFormattedCreationDate(), 4);

        root.add(form, BorderLayout.CENTER);
        JButton backButton = GuiHelper.secondaryButton("Back");
        backButton.addActionListener(e -> GuiHelper.refresh(this, new DashboardPage(bankingService, account)));
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.add(backButton);
        root.add(footer, BorderLayout.SOUTH);
        add(root);
    }

    private void addRow(JPanel form, String label, String value, int row) {
        form.add(GuiHelper.label(label), GuiHelper.gbc(0, row));
        form.add(GuiHelper.label(value), GuiHelper.gbc(1, row));
    }
}
