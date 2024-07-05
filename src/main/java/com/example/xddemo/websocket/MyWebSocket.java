package com.example.xddemo.websocket;

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * Author: xuedong
 * Date: 2024/7/3
 */
@ServerEndpoint(path = "/ws", port = "8987", host = "127.0.0.1")
public class MyWebSocket {


    public static final Map<String, Session> USER_SESSION = new ConcurrentHashMap<>();

    @BeforeHandshake
    public void handshake(Session session, HttpHeaders headers, @RequestParam String req, @PathVariable String arg, @PathVariable Map pathMap) {
        session.setSubprotocols("stomp");
    }

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, @RequestParam String req, @PathVariable String arg, @PathVariable Map pathMap) {

        InetSocketAddress socketAddress = (InetSocketAddress) session.localAddress();
        // 获取 IP 地址
        String hostname = socketAddress.getHostName();
        String ipAddress = socketAddress.getAddress().getHostAddress();
        int port = socketAddress.getPort();
        InetSocketAddress socketAddress1 = (InetSocketAddress) session.remoteAddress();
        System.out.println("new connection");
        System.out.println(req);

        USER_SESSION.put(req, session);
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
        session.sendText("Hello Netty!");
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

}
