package gui;

import model.Account;
import model.Transaction;
import service.BankingService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

public class TransactionHistoryPage extends JFrame {
    private final BankingService bankingService;
    private final Account account;

    public TransactionHistoryPage(BankingService bankingService, Account account, boolean miniStatement) {
        this.bankingService = bankingService;
        this.account = account;
        GuiHelper.setupFrame(this, miniStatement ? "SecureBank ATM - Mini Statement" : "SecureBank ATM - Transaction History");

        JPanel root = GuiHelper.page(miniStatement ? "Mini Statement" : "Transaction History",
                miniStatement ? "Last 5 transactions" : "Complete account activity");

        JTable table = new JTable(buildModel(miniStatement));
        table.setRowHeight(26);
        table.getTableHeader().setReorderingAllowed(false);
        root.add(new JScrollPane(table), BorderLayout.CENTER);

        JButton backButton = GuiHelper.secondaryButton("Back");
        backButton.addActionListener(e -> GuiHelper.refresh(this, new DashboardPage(bankingService, account)));
        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.add(backButton);
        root.add(footer, BorderLayout.SOUTH);
        add(root);
    }

    private DefaultTableModel buildModel(boolean miniStatement) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Date", "Transaction Type", "Amount", "Description"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Transaction> transactions = account.getTransactions();
        int start = miniStatement ? Math.max(0, transactions.size() - 5) : 0;
        for (int i = transactions.size() - 1; i >= start; i--) {
            Transaction transaction = transactions.get(i);
            model.addRow(new Object[]{
                    transaction.getFormattedDateTime(),
                    transaction.getType(),
                    String.format("Rs. %.2f", transaction.getAmount()),
                    transaction.getDescription()
            });
        }
        return model;
    }
}
