package com.example.xddemo.demo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;
    private Server server = new Server();



    @PostConstruct
    public void init(){
        String jsonString = JSON.toJSONString(this);
        System.out.println(jsonString);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Data
    public static class Server {
        private String address;
        private int port;

        private Boolean isDefault;

        // Getters and setters
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}

