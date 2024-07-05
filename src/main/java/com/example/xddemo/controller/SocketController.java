package com.example.xddemo.controller;

import com.example.xddemo.websocket.MyWebSocket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yeauty.pojo.Session;

/**
 * Author: xuedong
 * Date: 2024/7/5
 */
@RestController
public class SocketController {


    @PostMapping(value = "/send")
    public String sendMessage(@RequestParam String text, @RequestParam String userId) {
        Session session = MyWebSocket.USER_SESSION.get(userId);
        session.sendText(text);
        return text;

    }
}
