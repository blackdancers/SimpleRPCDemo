package com.tyue.rpc.service.exporter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ExporterTask implements Runnable {
    Socket client = null;

    public ExporterTask(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        try {

            input = new ObjectInputStream(client.getInputStream());
            String interfaceName = input.readUTF();
            Class<?> service = Class.forName(interfaceName);
            String methodName = input.readUTF();
            Class<?>[] paramTypes =(Class<?>[]) input.readObject();
            Object[] arguments = (Object[]) input.readObject();
            Method method = service.getMethod(methodName, paramTypes);
            Object result = method.invoke(service.newInstance(), arguments);

            output = new ObjectOutputStream(client.getOutputStream());
            output.writeObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null!=output){
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=input){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=client){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

















































