package com.tyue.rpc.service.exporter;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * RPC-->Remote Procedure Call ,它是一种进程间通信的方式，允许像调用本地服务一样调用远程服务。
 *
 *
 * RPC服务端服务发布者,主要职责如下：
 *  1，作为服务端，监听客户端TCP连接，接收到新的客户端连接之后，将其封装成Task，由线程池执行
 *  2，将客户端发送的码流反序列化成对象，反射调用服务实现者，获取执行结果
 *  3，将执行结果对象反序列化，通过Socket发送给客户端
 *  4，远程服务调用完成之后，释放Socket连接资源，防止句柄泄露
 *
 */
public class RPCExporter {

    static Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * @param hostName
     * @param port
     * @throws Exception
     */
    public static void exporter(String hostName, int port) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(hostName, port));
        while (true) {
            executor.execute(new ExporterTask(server.accept()));
        }
    }


}




























