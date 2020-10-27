package com.github.roman1306.transfer.io;

import com.github.roman1306.transfer.entity.Account;
import com.github.roman1306.transfer.exception.AccountIOException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountReader extends AccountIO {
    public AccountReader(String path) {
        super(path);
    }

    public AccountReader(String path, String format) {
        super(path, format);
    }

    public List<Account> readAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        File dir = new File(path);

        File[] files = dir.listFiles();

        if (files == null) {
            return Collections.emptyList();
        }

        for (File file : files) {
            accountList.add(readAccountByFileName(file.getName()));
        }

        return accountList;
    }

    private Account readAccountByFileName(String fileName) {
        try (ObjectInputStream inObj = new ObjectInputStream(new FileInputStream(path + "\\" + fileName))) {

            return (Account) inObj.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new AccountIOException(e.getMessage());
        }
    }

    public long readSumBalancesAccounts() {
        return readAllAccounts().stream().mapToLong(Account::getBalance).sum();
    }
}
