package com.tyue.rpc.service.provider;
/**
 * Created by blackdancer on 2017/6/8.
 *
 * 实现简单的RPC框架
 * 下面通过Java原生的序列化、Socket通信、动态代理和反射机制，实现最简单的RPC框架：它由三部分组成：
 * 1，服务提供者：运行在服务端，负责提供服务接口定义和服务实现类；
 * 2，服务发布者：运行在RPC服务端，负责将本地服务发布成远程服务，供其它消费者调用；
 * 3，本地服务代理：运行在RPC客户端，通过代理调用远程服务提供者，然后将结果封装返回给本地消费者。
 */
public interface EchoService {
    String echo(String ping);
}
