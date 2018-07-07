package cn.wang;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class RemoteClient {
	public static void invoke(RemoteCall call) throws Exception {
		Socket socket = new Socket("localhost",6000);
		OutputStream out = socket.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		InputStream in = socket.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(in);
		oos.writeObject(call);
		RemoteCall call2 = (RemoteCall) ois.readObject();
		System.out.println(call2.getResult());
		oos.close();
		ois.close();
		socket.close();
	}
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("请输入您要进行的操作:");
		System.out.println("1、存款");
		System.out.println("2、取款");
		System.out.println("3、转账");
		Server server = new Server();
		int choice = 0;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		choice = Integer.parseInt(bf.readLine());
		switch(choice) {
		case 1:
			System.out.println("请输入存款金额:");
			float money = Float.parseFloat(bf.readLine());
			RemoteCall call1 = new RemoteCall("cn.wang.RPCServiceImpl","deposit",new Class[] {float.class,cn.wang.Account.class},new Object[] {money,server.a1});
			invoke(call1);
			break;
		case 2:
//			System.out.println("请输入您的账户id:");
//			String id2 = bf.readLine();
			System.out.println("请输入取款金额");
			float money2 = Float.parseFloat(bf.readLine());
			RemoteCall call2 = new RemoteCall("cn.wang.RPCServiceImpl","withdraw",new Class[] {float.class,Account.class},new Object[] {money2,server.a1});
			invoke(call2);
			break;
		case 3:
//			System.out.println("请输入你的账户id:");
//			String id3 = bf.readLine();
//			System.out.println("请输入接收方账户id:");
//			String id4 = bf.readLine();
			System.out.println("请输入转账金额:");
			float money3 = Float.parseFloat(bf.readLine());
			RemoteCall call3 = new RemoteCall("cn.wang.RPCServiceImpl","transfer",new Class[] {float.class,Account.class,Account.class},new Object[] {money3,server.a1,server.a2});
			invoke(call3);
			break;
		default:
			System.out.println("您的输入有误！");
			break;
		}
	}

}
