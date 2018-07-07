package wang;

import java.io.Serializable;

public class Account implements Serializable{
	private static final long serialVersionUID = 42L;
	private int accountnumber;
	private float balance;
	public Account(){};
	public Account(int accountnumber,float balance){
		this.accountnumber = accountnumber;
		this.balance = balance;
	}
	
	public int getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(int accountnumber) {
		this.accountnumber = accountnumber;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	

}
