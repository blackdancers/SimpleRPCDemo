package com.tyue.rpc.service;

import com.tyue.rpc.service.exporter.RPCExporter;
import com.tyue.rpc.service.importer.RPCImporter;
import com.tyue.rpc.service.provider.EchoService;
import com.tyue.rpc.service.provider.impl.EchoServiceImpl;

import java.net.InetSocketAddress;

public class RPCTest {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RPCExporter.exporter("localhost",8088);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        RPCImporter<EchoService> importer = new RPCImporter<>();
        EchoService echoService = importer.importer(EchoServiceImpl.class,
                new InetSocketAddress("localhost",8088));
        System.out.println(echoService.echo("Are you OK?"));
    }
}































