//package cn.wang;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
//public class ServerProxy {
//    private RPCService ri = new RPCServiceImpl();
//    public RPCService getProxy(){
//        return (RPCService) Proxy.newProxyInstance(ServerProxy.class.getClassLoader(), ri.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if(method.getName().equals("deposit")){
//                    System.out.println("代理启动！");
//                    return method.invoke(ri,args);
//                }
//                if(method.getName().equals("withdraw")){
//                    System.out.println("代理启动！");
//                    return method.invoke(ri,args);
//                }
//                if(method.getName().equals("transfer")){
//                    System.out.println("代理启动！");
//                    return method.invoke(ri,args);
//                }
//                return null;
//            }
//        });
//    }
//}
