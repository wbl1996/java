package cn.wang;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RPCServiceImpl implements RPCService {
	public String deposit(float number,Account account ) {
		account.setBalance(account.getBalance()+number);
		return "您的余额为:"+account.getBalance();
	}
	public String withdraw(float number,Account account) {
		account.setBalance(account.getBalance()-number);
		return "您的余额为:"+account.getBalance();
	}
	public String transfer(float number,Account account1,Account account2) {
		account1.setBalance(account1.getBalance()-number);
		account2.setBalance(account2.getBalance()+number);
		return "您的余额为"+account1.getBalance();
	}
}
