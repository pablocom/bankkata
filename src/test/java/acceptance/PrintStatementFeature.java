package acceptance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pablocom.bankkata.Console;
import pablocom.bankkata.Account;
import pablocom.bankkata.Clock;
import pablocom.bankkata.StatementPrinter;
import pablocom.bankkata.TransactionRepository;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {
    @Mock Console console;
    @Mock Clock clock;

    private Account account;

    @Before
    public void initialise() {

        var transactionRepository = new TransactionRepository(clock);
        var statementPrinter = new StatementPrinter(console);
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void print_statement_containing_all_transactions() {
        GivenDepositAt("01/04/2014", 1000);
        GivenWithdrawAt("02/04/2014", 100);
        GivenDepositAt("10/04/2014", 500);

        account.printStatement();

        var inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }

    private void GivenWithdrawAt(String date, int amount) {
        given(clock.todayAsString()).willReturn(date);
        account.withDraw(amount);
    }

    private void GivenDepositAt(String date, int amount) {
        given(clock.todayAsString()).willReturn(date);
        account.deposit(amount);
    }
}
