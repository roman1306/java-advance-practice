package com.github.roman1306.transfer.repository;

import com.github.roman1306.transfer.entity.Account;

import java.util.Map;
import java.util.TreeMap;

public class AccountRepository {
    Map<String, Account> accountsMap = new TreeMap<>();

    public AccountRepository(Map<String, Account> accountsMap) {
        this.accountsMap = accountsMap;
    }

    public Account getAccountById(String id) {
        return accountsMap.getOrDefault(id, null);
    }

    public synchronized boolean addAccount(Account account) {
        if (accountsMap.containsKey(account.getId())) {
            return false;
        }

        accountsMap.put(account.getId(), account);
        return true;
    }
}
