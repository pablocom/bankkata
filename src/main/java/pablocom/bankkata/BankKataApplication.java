package pablocom.bankkata;

public class BankKataApplication {
    public static void main(String[] args) {
        var clock = new Clock();
        var transactionRepository = new TransactionRepository(clock);

        var console = new Console();
        var statementPrinter = new StatementPrinter(console);

        var account = new Account(transactionRepository, statementPrinter);

        account.deposit(1000);
        account.withDraw(100);
        account.deposit(500);

        account.printStatement();
    }
}
