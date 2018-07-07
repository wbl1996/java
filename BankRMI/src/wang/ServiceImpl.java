package wang;

import javax.xml.crypto.Data;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceImpl extends UnicastRemoteObject implements RemoteInterface {
	//DataBase db = new DataBase();

	public ServiceImpl() throws RemoteException{
	}
	public String withdraw(int accountNumber, float number) throws RemoteException {
		// TODO Auto-generated method stub
		DataBase db = new DataBase();
		db.getConnetion();
		String sql1 = "select * from account where id=?";
		String sql2 = "update account set balance =?where id =?";
		ResultSet rs;
		PreparedStatement ps ;
		float balance1 = 0;
		Account account = new Account();
		account.setAccountnumber(accountNumber);
		try{
			ps = db.PreparedStmt(sql1);
			ps.setInt(1,accountNumber);
			rs = ps.executeQuery();
			while(rs.next()){
				balance1 = rs.getFloat(2);
			}
			ps = db.PreparedStmt(sql2);
			ps.setFloat(1,balance1-number);
			ps.setInt(2,accountNumber);
			ps.executeUpdate();
			ps = db.PreparedStmt(sql1);
			ps.setInt(1,accountNumber);
			rs = ps.executeQuery();
			while(rs.next()){
				float balance2 = rs.getFloat(2);
				account.setBalance(balance2);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return "您的余额为:"+account.getBalance()+"元!";
	}
	public String deposit(int accountNumber,float number) throws RemoteException{
		DataBase db = new DataBase();
		db.getConnetion();
		Account account = new Account();
		account.setAccountnumber(accountNumber);
		PreparedStatement ps;
		ResultSet rs ;
		float balance = 0;
		String sql1 = "select *from account where id=?";
		String sql2 = "update account set balance=? where id=?";
		try{
			ps = db.PreparedStmt(sql1);
			ps.setInt(1,accountNumber);
			rs = ps.executeQuery();
			while(rs.next()){
				balance = rs.getFloat(2);
			}
			ps = db.PreparedStmt(sql2);
			ps.setFloat(1,balance+number);
			ps.setInt(2,accountNumber);
			ps.executeUpdate();
			ps = db.PreparedStmt(sql1);
			ps.setInt(1,accountNumber);
			rs = ps.executeQuery();
			while(rs.next()){
				account.setBalance(rs.getFloat(2));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return "您的余额为:"+account.getBalance()+"元!";
	}
	public String transfer(int accountNumber1,int accountNumber2,float number) throws RemoteException{
		DataBase db = new DataBase();
		db.getConnetion();
		PreparedStatement ps;
		ResultSet rs;
		Account account1 = new Account();
		Account account2 = new Account();
		account1.setAccountnumber(accountNumber1);
		account2.setAccountnumber(accountNumber2);
		String sql1 = "update account set balance = ? where id = ?";
		String sql2 = "select * from account where id = ?";
		float balance1 = 0,balance2 = 0;
		try{
			ps = db.PreparedStmt(sql2);
			ps.setInt(1,accountNumber1);
			rs = ps.executeQuery();
			while(rs.next()){
				balance1 = rs.getFloat(2);
			}
			account1.setBalance(balance1-number);
			ps = db.PreparedStmt(sql1);
			ps.setFloat(1,balance1-number);
			ps.setInt(2,accountNumber1);
			ps.executeUpdate();
			ps = db.PreparedStmt(sql2);
			ps.setInt(1,accountNumber2);
			rs = ps.executeQuery();
			while(rs.next()){
				balance2 = rs.getFloat(2);
			}
			ps = db.PreparedStmt(sql1);
			ps.setFloat(1,balance2+number);
			ps.setInt(2,accountNumber2);
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return "转账成功!\n您的余额为:"+account1.getBalance()+"元!";
	}

}
