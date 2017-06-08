package com.tyue.rpc.service;

import com.tyue.rpc.service.exporter.RPCExporter;
import com.tyue.rpc.service.importer.RPCImporter;
import com.tyue.rpc.service.provider.EchoService;
import com.tyue.rpc.service.provider.impl.EchoServiceImpl;

import java.net.InetSocketAddress;

/**
 * 测试：首先创建一个异步发布服务端的线程启动，用于接收RPC客户端的请求，根据请求参数调用服务实现类，返回结果给客户端。
 * 随后，创建客户端服务代理类，构造RPC请求参数，发起RPC调用，将调用结果输出到控制台上。
 *
 *
 *
 */
public class RPCTest {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                //用于接收RPC客户端的请求
                RPCExporter.exporter("localhost", 8088);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        //客户端服务代理类
        RPCImporter<EchoService> importer = new RPCImporter<>();


        EchoService echoService = importer.importer(EchoServiceImpl.class,new InetSocketAddress("localhost", 8088));


        System.out.println(echoService.echo("Are you OK?"));
    }
}































