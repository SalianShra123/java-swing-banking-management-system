package gui;

import model.Account;
import service.BankingService;

public class MiniStatementPage extends TransactionHistoryPage {
    public MiniStatementPage(BankingService bankingService, Account account) {
        super(bankingService, account, true);
    }
}
