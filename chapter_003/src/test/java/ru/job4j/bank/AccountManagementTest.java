package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Belokursky
 * @since 16.09.17.
 */
public class AccountManagementTest {

    User testUser = new User("Test1", "8889-777777");
    Account testAccount = new Account(1234.0, "1111-2222-3333-4444");
    private AccountManagement am = new AccountManagement();

    @Test
    public void addUserTest() {
        boolean expected = true;
        am.addUser(testUser);
        boolean result = am.getBankMap().containsKey(testUser);
        assertThat(result, is(expected));
    }

    @Test
    public void deleteUserTest() {
        boolean expected = false;
        am.deleteUser(testUser);
        boolean result = am.getBankMap().containsKey(testUser);
        assertThat(result, is(expected));
    }

    @Test
    public void addAccountToUserTest() {
        am.addUser(testUser);
        am.addAccountToUser(testUser, testAccount);
        boolean expected = true;
        boolean result = am.getBankMap().get(testUser).contains(testAccount);
        assertThat(result, is(expected));
    }

    @Test
    public void deleteAccountFromUserTest() {
        am.addUser(testUser);
        am.addAccountToUser(testUser, testAccount);
        boolean expected = false;
        am.deleteAccountFromUser(testUser, testAccount);
        boolean result = am.getBankMap().get(testUser).contains(testAccount);
        assertThat(result, is(expected));

    }

    @Test
    public void transferMoneyTest() {
        am.addUser(testUser);
        am.addAccountToUser(testUser, testAccount);
        User testUser2 = new User("test2", "1111-222222");
        Account testAccount2 = new Account(100, "1111-1111-1111-1111");
        am.addUser(testUser2);
        am.addAccountToUser(testUser2, testAccount2);
        boolean result = am.transferMoney(testUser, testAccount, testUser2, testAccount2, 1.0);
        boolean expected = true;
        assertThat(result, is(expected));
    }
}
