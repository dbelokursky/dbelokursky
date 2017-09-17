package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dmitry Belokursky
 * @since 16.09.17.
 */
public class AccountManagement {

    private Map<User, List<Account>> bankMap = new HashMap<>();

    public Map<User, List<Account>> getBankMap() {
        return bankMap;
    }

    public void addUser(User user) {
        bankMap.put(user, new ArrayList<>());
    }

    public void deleteUser(User user) {
        bankMap.remove(user);

    }

    public void addAccountToUser(User user, Account account) {
        if (bankMap.containsKey(user)) {
            List<Account> tmp = bankMap.get(user);
            if (!tmp.contains(account)) {
                tmp.add(account);
                bankMap.put(user, tmp);
            }
        }
    }

    public void deleteAccountFromUser(User user, Account account) {
        if (bankMap.containsKey(user)) {
            List<Account> tmp = bankMap.get(user);
            if (tmp.contains(account)) {
                tmp.remove(account);
                bankMap.put(user, tmp);
            }
        }
    }

    public List<Account> getUserAccounts(User user) {
        return bankMap.get(user);
    }

    public boolean transferMoney(User scrUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;
        if (bankMap.containsKey(scrUser) && bankMap.containsKey(dstUser)) {
            List<Account> srcUserAccounts = getUserAccounts(scrUser);
            List<Account> dstUserAccounts = getUserAccounts(dstUser);
            if (srcUserAccounts.contains(srcAccount) && dstUserAccounts.contains(dstAccount)) {
                if (srcAccount.getValue() >= amount) {
                    srcUserAccounts.remove(srcAccount);
                    dstUserAccounts.remove(dstAccount);
                    srcAccount.setValue(srcAccount.getValue() - amount);
                    dstAccount.setValue(dstAccount.getValue() + amount);
                    srcUserAccounts.add(srcAccount);
                    dstUserAccounts.add(dstAccount);
                    bankMap.put(scrUser, srcUserAccounts);
                    bankMap.put(dstUser, dstUserAccounts);
                    result = true;
                }
            }
        }
        return result;
    }
}
