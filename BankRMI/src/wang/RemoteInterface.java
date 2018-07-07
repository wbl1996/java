package wang;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote{
	public String withdraw(int accountNumber, float number) throws RemoteException;
	public String deposit(int accountNumber,float number) throws RemoteException;
	public String transfer(int accountNumber1,int accountNumber2,float number) throws RemoteException;
}
