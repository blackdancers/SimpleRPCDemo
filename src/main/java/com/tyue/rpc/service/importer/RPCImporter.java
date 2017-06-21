package com.tyue.rpc.service.importer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * RPC客户端本地服务代理,主要功能如下：
 * 1，将本地的接口调用转换成JDK的动态代理，在动态代理中实现接口的远程调用。
 * 2，创建Socket客户端，根据指定的地址连接远程服务提供者
 * 3，将远程服务调用所需的接口类，方法名、参数列表等编码后发送给服务提供者
 * 4，同步阻塞等待服务端返回应答，获取应答之后返回
 * @param <T>
 */
public class RPCImporter<T> {

    /**
     *
     * @param serviceClass
     * @param address
     * @return
     */
    public T importer(final Class<?> serviceClass, final InetSocketAddress address) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),

                new Class<?>[]{serviceClass.getInterfaces()[0]}, (proxy, method, args) -> {
                    Socket socket = null;
                    ObjectOutputStream output = null;
                    ObjectInputStream input = null;
                    try {
                        socket = new Socket();
                        socket.connect(address);
                        output = new ObjectOutputStream(socket.getOutputStream());

                        output.writeUTF(serviceClass.getName());//写入顺序
                        output.writeUTF(method.getName());
                        output.writeObject(method.getParameterTypes());
                        output.writeObject(args);

                        input = new ObjectInputStream(socket.getInputStream());
                        return input.readObject();
                    }finally {
                        if (null!=socket){
                            socket.close();
                        }
                        if (null!=output){
                            output.close();
                        }
                        if (null!=input){
                            input.close();
                        }
                    }
                });
    }
}























