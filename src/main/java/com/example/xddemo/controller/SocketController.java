package com.example.xddemo.controller;

import com.example.xddemo.websocket.MyWebSocket;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yeauty.pojo.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: xuedong
 * Date: 2024/7/5
 */
@RestController
public class SocketController {


    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam String text, @RequestParam String userId) {
        Session session = MyWebSocket.USER_SESSION.get(userId);
        ChannelFuture channelFuture = session.sendText(text);
        channelFuture.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                System.out.println("Message sent successfully");
            } else {
                System.out.println("Message send failed");
                future.cause().printStackTrace();
            }
        });
        return text;

    }


    @RequestMapping("/doTest")
    @ResponseStatus(HttpStatus.FOUND)
    public void redirect(HttpServletResponse response, HttpServletRequest request) {
        response.setHeader("location", "https://www.baidu.com");
    }


}
