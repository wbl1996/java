package cn.wang;

public interface RPCService{
	public String deposit(float number, Account account);
	public String withdraw(float number, Account account);
	public String transfer(float number, Account account1, Account account2);
}
