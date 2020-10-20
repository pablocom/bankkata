package acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pablocom.Console;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementFeature {

    @Mock Console console;

    @Test
    public void print_statement_containing_all_transactions() {

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
        verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
