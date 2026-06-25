package gui;

import model.Account;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;

public class BenefitsPage extends JFrame {
    public BenefitsPage(BankingService bankingService, Account account) {
        GuiHelper.setupFrame(this, "SecureBank ATM - Benefits");

        JPanel root = GuiHelper.page("Account Benefits", "Polymorphism in action through account-specific behavior");
        JTextArea area = new JTextArea(account.calculateBenefits());
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        area.setBackground(GuiHelper.BACKGROUND);
        area.setForeground(GuiHelper.TEXT);
        root.add(area, BorderLayout.CENTER);

        JButton backButton = GuiHelper.secondaryButton("Back");
        backButton.addActionListener(e -> GuiHelper.refresh(this, new DashboardPage(bankingService, account)));
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.add(backButton);
        root.add(footer, BorderLayout.SOUTH);

        add(root);
    }
}
