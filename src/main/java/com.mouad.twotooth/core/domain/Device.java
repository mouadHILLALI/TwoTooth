package com.mouad.twotooth.core.domain;

public class Device {

    private String name;
    private String ip;
    private int port;

    public Device(String ip, String name , int port) {
        this.ip = ip;
        this.name = name;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

}
