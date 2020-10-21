package pablocom.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pablocom.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {
    private static final List<Transaction> NO_TRANSACTIONS = EMPTY_LIST;
    @Mock Console console;
    private StatementPrinter statementPrinter;

    @Before
    public void initialise() {
        statementPrinter = new StatementPrinter(console);
    }

    @Test
    public void always_print_header() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(console).printLine("DATE | AMOUNT | BALANCE");
    }

    @Test
    public void prints_transactions_ordered_by_date_descendent() {
        var transactions = transactionsContaining(
                deposit("01/04/2014", 1000),
                withdrawal("02/04/2014", 100),
                deposit("10/04/2014", 500));

        statementPrinter.print(transactions);

        var inOrder = inOrder(console);
        inOrder.verify(console).printLine("DATE | AMOUNT | BALANCE");
        inOrder.verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        inOrder.verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        inOrder.verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }

    private List<Transaction> transactionsContaining(Transaction... transactions) {
        return Arrays.asList(transactions);
    };

    private Transaction deposit(String date, int amount) {
        return new Transaction(date, amount);
    }

    private Transaction withdrawal(String date, int amount) {
        return new Transaction(date, -amount);
    }
}