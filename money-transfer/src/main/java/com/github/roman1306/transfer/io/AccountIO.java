package com.github.roman1306.transfer.io;

abstract public class AccountIO {
    protected String path;
    protected String format = "bin";

    public AccountIO(String path) {
        this.path = path;
    }

    public AccountIO(String path, String format) {
        this.path = path;
        this.format = format;
    }
}
