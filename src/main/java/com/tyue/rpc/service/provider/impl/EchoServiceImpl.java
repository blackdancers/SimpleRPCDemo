package com.tyue.rpc.service.provider.impl;

import com.tyue.rpc.service.provider.EchoService;

/**
 * Created by blackdancer on 2017/6/8.
 */
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return ping !=null ?ping +"--> I am OK.":"I not OK ~^_^~";
    }
}
