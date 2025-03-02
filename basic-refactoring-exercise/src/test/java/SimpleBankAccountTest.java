import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final int INITIAL_BALANCE = 0;
    private static final int FIRST_DEPOSIT = 100;
    private static final int INVALID_USER_ID = 2;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        assertEquals(FIRST_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWrongIdDeposit() {
        final int invalidDeposit = 50;

        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT); // Deposito valido
        bankAccount.deposit(INVALID_USER_ID, invalidDeposit); // Tentativo di deposito con ID errato

        final int expectedBalance = INITIAL_BALANCE + FIRST_DEPOSIT;

        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void testWithdrawWithoutFee() {
        final int withdrawAmount = 50;

        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        bankAccount.withdraw(accountHolder.getId(), withdrawAmount);

        final int expectedBalance = INITIAL_BALANCE + FIRST_DEPOSIT - withdrawAmount;

        assertEquals(expectedBalance, bankAccount.getBalance());
    }


    @Test
    void testWrongIdWithdraw() {
        final int firstWithdrawAmount = 70;

        bankAccount.deposit(accountHolder.getId(), FIRST_DEPOSIT);
        bankAccount.withdraw(INVALID_USER_ID, firstWithdrawAmount);

        final int expectedBalance = INITIAL_BALANCE + FIRST_DEPOSIT;

        assertEquals(expectedBalance, bankAccount.getBalance());
    }
}
