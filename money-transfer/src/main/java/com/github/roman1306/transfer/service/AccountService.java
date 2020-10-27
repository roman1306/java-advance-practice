package com.github.roman1306.transfer.service;

import com.github.roman1306.transfer.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger("logger");

    public static Account createAccount(String id, long balance) {
        return new Account(id, balance);
    }

    public static boolean withdrawalAndReplenishment(Account from, Account to, long sum) {
        if (from.equals(to)) {
            logger.error("Operation failed: accounts equal {} = {} ", from.getId(), to.getId());
            return false;
        }

        logger.info("Operation begin from {} to {} summa {}", from.getId(), to.getId(), sum);

        if (from.getBalance() < sum) {
            logger.error("Operation failed: not enough balance = {} summa = {}", to.getId(), sum);
            return false;
        } else {
            from.setBalance(from.getBalance() - sum);
            to.setBalance(to.getBalance() + sum);
            logger.info("Operation successful balance from = {} balance to = {}", from.getBalance(), to.getBalance());
            return true;
        }

    }
}
