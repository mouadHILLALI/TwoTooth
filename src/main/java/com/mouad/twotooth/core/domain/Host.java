package com.mouad.twotooth.core.domain;

import java.util.HashSet;
import java.util.Set;

public class Host {
    private final String name;
    private final String ipAddress;
    private final int port;
    private final Set<Device> connectedDevices = new HashSet<>();

    public Host(String name, String ipAddress, int port) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public Set<Device> getConnectedDevices() {
        return connectedDevices;
    }

    public void addDevice(Device device) {
        connectedDevices.add(device);
    }

    public void removeDevice(Device device) {
        connectedDevices.remove(device);
    }
}
