package com.github.roman1306.transfer.io;

import com.github.roman1306.transfer.entity.Account;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class AccountWriter extends AccountIO {

    public AccountWriter(String path) {
        super(path);
    }

    public AccountWriter(String path, String format) {
        super(path, format);
    }

    public boolean writeAccount(Account account) {
        try (ObjectOutputStream outObj = new ObjectOutputStream(
                new FileOutputStream(path + "\\" + account.getId() + "." + format))) {

            outObj.writeObject(account);
            return true;
        } catch (IOException e) {
            throw new IllegalArgumentException("Account not recorded");
        }
    }

    public void initAccount() {
        new File(path).mkdirs();

        for (int i = 1; i <= 10; i++) {
            writeAccount(new Account(String.valueOf(i), 5000));
        }
    }
}
