package com.example.xddemo.config;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

/**
 * Author: xuedong
 * Date: 2024/8/1
 */
public class DubboTest {


    public static void main(String[] args) throws Exception {
        sayHello();


    }

    public static  void sayHello() throws Exception {
        URL url = URL.valueOf("dubbo://localhost/test");
        Protocol adaptiveProtocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();




        while (true){

        }

    }
}
