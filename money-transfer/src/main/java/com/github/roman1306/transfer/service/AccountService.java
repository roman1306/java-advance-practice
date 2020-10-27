package com.github.roman1306.transfer.service;

import com.github.roman1306.transfer.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger("logger");

    public static Account createAccount(String id, long balance) {
        return new Account(id, balance);
    }

    public static boolean transactionOperation(Account from, Account to, long sum) {
        if (from.equals(to)) {
            logger.error("Operation failed: accounts equal {} = {} ", from.getId(), to.getId());
            return false;
        }

        logger.info("Operation begin from {} to {} summa {}", from.getId(), to.getId(), sum);

        if (withdrawal(from, sum)) {

            replenishment(to, sum);
            logger.info("Operation successful balance from = {} balance to = {}", from.getBalance(), to.getBalance());
            return true;
        }
        return false;

    }

    private static boolean withdrawal(Account from, long sum) {
        if (from.getBalance() >= sum) {

            from.setBalance(from.getBalance() - sum);
            return true;
        }
        logger.error("Operation failed: not enough from balance = {} summa = {}", from.getId(), sum);
        return false;
    }

    private static void replenishment(Account to, long sum) {
        to.setBalance(to.getBalance() + sum);
    }
}
