package com.github.roman1306.transfer.service;

import com.github.roman1306.transfer.entity.Account;

import java.util.logging.Logger;

public class AccountService {
    private static Logger logger = Logger.getLogger(AccountService.class.getName());

    public static Account createAccount(String id, long balance) {
        return new Account(id, balance);
    }

    public static boolean withdrawalAndReplenishment(Account from, Account to, long sum) {
        logger.info("Operation begin from " + from.getId() +
                " to " + to.getId() + " summa " + sum);

        if (from.getBalance() < sum) {
            logger.warning("Operation failed: account id " + from.getId()
                    + " not enough balance " + from.getBalance());
            return false;
        } else {
            from.setBalance(from.getBalance() - sum);
            to.setBalance(to.getBalance() + sum);
            logger.info("Operation successful");
            return true;
        }

    }
}
