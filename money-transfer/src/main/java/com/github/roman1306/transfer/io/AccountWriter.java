package com.github.roman1306.transfer.io;

import com.github.roman1306.transfer.entity.Account;

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

    public void writeAccount(Account account) {
        try (ObjectOutputStream outObj = new ObjectOutputStream(
                new FileOutputStream(path + "\\" + account.getId() + "." + format))) {

            outObj.writeObject(account);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
