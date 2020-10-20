package pablocom.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {
    @Mock Clock clock;

    private TransactionRepository transactionRepository;

    private static final String TODAY = "20/09/1996";

    @Before
    public void setUp() {
        transactionRepository = new TransactionRepository(clock);
        given(clock.todayAsString()).willReturn(TODAY);
    }

    @Test
    public void create_and_store_a_transaction() {
        transactionRepository.addDeposit(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(new Transaction(TODAY, 100)));
    }

    @Test
    public void create_and_store_a_withdrawal_transactions() {
        transactionRepository.addWithdrawal(100);

        List<Transaction> transactions = transactionRepository.allTransactions();

        assertThat(transactions.size(), is(1));
        assertThat(transactions.get(0), is(new Transaction(TODAY, -100)));
    }
}