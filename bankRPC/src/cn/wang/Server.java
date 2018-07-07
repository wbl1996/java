package cn.wang;
import java.io.*;
import java.lang.reflect.Method;
import java.net.*;
import java.util.*;

public class Server{
	float balance = 4000;
	Account a1 = new Account("123",balance);
	Account a2 = new Account("234",balance);
	static Map remoteObjects = new HashMap();
	public void Register(String className,Object remoteObject) {
		remoteObjects.put(className, remoteObject);
	}
	public  static RemoteCall invoke(RemoteCall call) {
		Object result = null;
		try {
			String className = call.getClassName();
			String methodName = call.getMethodName();
			Object[] params = call.getParams();
			Class<?> classType = Class.forName("cn.wang.RPCServiceImpl");
			Class[] paramTypes = call.getParamTypes();
			Method method = classType.getDeclaredMethod(methodName,paramTypes);
			Object remoteObject = remoteObjects.get(className);
			if(remoteObject==null){
				throw new Exception(className+"的远程对象不存在！");
			}else {
				result = method.invoke(remoteObject, params);
			}
		} catch (Exception e){
			result =  e;
		}
		call.setResult(result);
		return call;
	}
//	public void service() throws Exception {
//		ServerSocket serverSocket;
//		serverSocket = new ServerSocket(4000);
//		System.out.println("服务器启动...");
//		Socket socket = serverSocket.accept();
//		InputStream in = socket.getInputStream();
//		ObjectInputStream ois = new ObjectInputStream(in);
//		OutputStream os = socket.getOutputStream();
//		ObjectOutputStream oos = new ObjectOutputStream(os);
//		RemoteCall remoteCallObj = (RemoteCall) ois.readObject();
//		System.out.println(remoteCallObj);
//		remoteCallObj = invoke(remoteCallObj);
//		oos.writeObject(remoteCallObj);
//		}
}
