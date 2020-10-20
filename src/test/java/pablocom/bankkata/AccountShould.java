package pablocom.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {
    @Mock TransactionRepository transactionRepository;
    @Mock StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void initialise() {
        account = new Account(transactionRepository, statementPrinter);
    }

    @Test
    public void stores_deposit_in_transaction() {
        account.deposit(100);

        verify(transactionRepository).addDeposit(100);
    }

    @Test
    public void stores_withdrawal_in_transaction() {
        account.withDraw(100);

        verify(transactionRepository).addWithdrawal(100);
    }

    @Test
    public void print_a_statement() {
        List<Transaction> transactions = Collections.singletonList(new Transaction("20/09/1996", 200));
        given(transactionRepository.allTransactions()).willReturn(transactions);

        account.printStatement();

        verify(statementPrinter).print(transactions);
    }
}