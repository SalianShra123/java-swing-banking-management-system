import gui.LoginPage;
import service.BankingService;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // The app still works with Swing's default look and feel.
            }
            BankingService bankingService = new BankingService();
            new LoginPage(bankingService).setVisible(true);
        });
    }
}
