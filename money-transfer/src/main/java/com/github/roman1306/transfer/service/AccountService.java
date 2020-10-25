package com.github.roman1306.transfer.service;

import com.github.roman1306.transfer.entity.Account;

import java.util.logging.Logger;

public class AccountService {

    private static final Logger logger = Logger.getLogger(AccountService.class.getName());

    public static Account createAccount(String id, long balance) {
        return new Account(id, balance);
    }

    public static synchronized boolean sendMoney(Account from, Account to, long sum) {
        if (from.getBalance() < sum) {
            logger.warning("Недостаточно средств на счету " + from.getId());
            return false;
        }

        from.setBalance(from.getBalance() - sum);
        to.setBalance(to.getBalance() + sum);
        logger.info("Операция прошла успешно");
        return true;
    }

}
