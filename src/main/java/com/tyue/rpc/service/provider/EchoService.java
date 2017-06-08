package com.tyue.rpc.service.provider;
/**
 * Created by blackdancer on 2017/6/8.
 *
 * 实现简单的RPC框架
 * 下面通过Java原生的序列化、Socket通信、动态代理和反射机制，实现最简单的RPC框架：它由三部分组成：
 * 1，服务提供者
 * 2，服务发布者
 * 3，本地服务代理
 */
public interface EchoService {
    String echo(String ping);
}
