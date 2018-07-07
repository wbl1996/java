package com.wang.consumer.entities;
public class Account {
    private String accountname;
    private String password;
    private int balance;

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Account(String accountname, String password, int balance) {
        this.accountname = accountname;
        this.password = password;
        this.balance = balance;
    }
    public Account(){}
}
