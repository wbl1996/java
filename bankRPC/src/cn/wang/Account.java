package cn.wang;

import java.io.Serializable;

public class Account implements Serializable {
	String id;
	float balance;
	public Account() {}
	public Account(String id,float balance) {
		this.balance = balance;
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
}
