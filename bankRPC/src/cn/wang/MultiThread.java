package cn.wang;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThread {
    public static void main(String[] args){
        Server server = new Server();
       // ServerProxy sp = new ServerProxy();
       // RPCService r = sp.getProxy();
        server.Register("cn.wang.RPCServiceImpl",new RPCServiceImpl());
        ServerSocket serverSocket = null;
        System.out.println("服务器启动...");
        Socket socket = null;
        while(true){
            try {
                serverSocket = new ServerSocket(6000);
            }catch (Exception e){
                System.out.println("Error:"+e);
            }
            try{
                socket = serverSocket.accept();
            }catch (Exception e){
                System.out.println("连接失败！");
            }
            ServerThread st = new ServerThread(socket);
            Thread t = new Thread(st);
            t.start();
        }
    }
    static class ServerThread implements Runnable{
        private Socket socket = null;
        public ServerThread(Socket socket){
            this.socket = socket;
        }
        public void run(){
            try {
                InputStream in = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in);
                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                RemoteCall remoteCallObj = (RemoteCall) ois.readObject();
                System.out.println(remoteCallObj);
                remoteCallObj = Server.invoke(remoteCallObj);
                oos.writeObject(remoteCallObj);
            }catch (Exception e){
                System.out.println("Error:"+e);
            }
        }
    }
}
