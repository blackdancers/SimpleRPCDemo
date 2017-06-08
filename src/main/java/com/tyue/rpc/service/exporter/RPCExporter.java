package com.tyue.rpc.service.exporter;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * RPC服务端服务发布者
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




























