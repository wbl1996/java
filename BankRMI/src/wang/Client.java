package wang;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
//	private static String at1 = "123";
//	private static float balance = 4000;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			RemoteInterface ri = (RemoteInterface) Naming.lookup("rmi://localhost:7777/rmi");
			System.out.println("请输入您要进行的操作:1、取款，2、存款，3、转账.");
			Scanner sc = new Scanner(System.in);
			int accountNumber = 0;
			float number;
			int num = sc.nextInt();
			switch (num){
				case 1:
					System.out.println("请输入您的账户号码:");
					accountNumber = sc.nextInt();
					System.out.println("请输入您要取的金额:");
					number = sc.nextFloat();
					System.out.println(ri.withdraw(accountNumber,number));
					break;
				case 2:
					System.out.println("请输入您的账户号码:");
					accountNumber = sc.nextInt();
					System.out.println("请输入您要存的金额:");
					number = sc.nextFloat();
					System.out.println(ri.deposit(accountNumber,number));
					break;
				case 3:
					System.out.println("请输入您的账户号码:");
					accountNumber = sc.nextInt();
					System.out.println("请输入对方的账户号码:");
					int accountNumber2 = sc.nextInt();
					System.out.println("请输入转账金额:");
					number = sc.nextFloat();
					System.out.println(ri.transfer(accountNumber,accountNumber2,number));
					break;
					default:System.out.println("您的输入有误，程序退出!");break;
			}
//			System.out.println("请输入您的账户号码:");
//			Scanner s = new Scanner(System.in);
//			int acountNumber = s.nextInt();
//			System.out.println("请输入您要取的金额:");
//			float number = s.nextFloat();
//			System.out.println(ri.withdraw(acountNumber, number));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
