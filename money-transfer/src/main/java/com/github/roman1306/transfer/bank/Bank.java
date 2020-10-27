package com.github.roman1306.transfer.bank;

import com.github.roman1306.transfer.entity.Account;
import com.github.roman1306.transfer.io.AccountReader;
import com.github.roman1306.transfer.io.AccountWriter;
import com.github.roman1306.transfer.repository.AccountRepository;
import com.github.roman1306.transfer.service.AccountService;

import java.util.List;

public class Bank {
    private final AccountReader accountReader;
    private final AccountWriter accountWriter;
    private final AccountRepository accountRepository;

    public Bank(AccountReader accountReader, AccountWriter accountWriter, AccountRepository accountRepository) {
        this.accountReader = accountReader;
        this.accountWriter = accountWriter;
        this.accountRepository = accountRepository;
        loadAccount();
    }

    private void loadAccount() {
        List<Account> accounts = accountReader.readAllAccounts();
        accountRepository.addAllAccount(accounts);
    }

    public void sendMoney(String idFrom, String idTo, long sum) {
        Account accountFrom = accountRepository.getAccountById(idFrom);
        Account accountTo = accountRepository.getAccountById(idTo);

        if (accountFrom == null || accountTo == null) {
            throw new IllegalArgumentException("Account Id null");
        }

        takeLocks(accountFrom, accountTo);

        try {
            if (AccountService.transactionOperation(accountFrom, accountTo, sum)) {
                accountWriter.writeAccount(accountFrom);
                accountWriter.writeAccount(accountTo);
            }
        } finally {
            accountFrom.unlock();
            accountTo.unlock();
        }
    }

    private void takeLocks(Account from, Account to) {
        boolean fromLockTake = false;
        boolean toLockTake = false;

        while (true) {
            try {
                fromLockTake = from.tryLock();
                toLockTake = to.tryLock();
            } finally {
                if (fromLockTake && toLockTake) {
                    return;
                }
                if (fromLockTake) {
                    from.unlock();
                }
                if (toLockTake) {
                    to.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
