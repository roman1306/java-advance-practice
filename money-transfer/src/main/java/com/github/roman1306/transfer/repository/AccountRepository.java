package com.github.roman1306.transfer.repository;

import com.github.roman1306.transfer.entity.Account;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccountRepository {
    Map<String, Account> accountsMap = new TreeMap<>();

    public boolean addAllAccount(List<Account> accountList) {
        for (Account account : accountList) {
            if (accountsMap.containsKey(account.getId())) {
                return false;
            }
        }

        for (Account account : accountList) {
            accountsMap.put(account.getId(), account);
        }
        return true;
    }

    public Account getAccountById(String id) {
        return accountsMap.get(id);
    }
}
