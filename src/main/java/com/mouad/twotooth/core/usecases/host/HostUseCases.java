package com.mouad.twotooth.core.usecases.host;

import com.mouad.twotooth.core.domain.Device;
import com.mouad.twotooth.core.domain.Host;
import com.mouad.twotooth.core.port.host.HostManagerPort;

public class HostUseCases {
    private final HostManagerPort hostManagerPort;
    private Host currentHost;

    public HostUseCases(HostManagerPort hostManagerPort) {
        this.hostManagerPort = hostManagerPort;
    }


    public void startParty(String name, String ip, int port) {
        if (currentHost != null) {
            throw new IllegalStateException("A party is already running!");
        }
        currentHost = new Host(name, ip, port);
        hostManagerPort.startHosting(currentHost);
    }

    public void addGuest(Device device) {
        if (currentHost == null) {
            throw new IllegalStateException("No active party to join.");
        }
        if (currentHost.getConnectedDevices().contains(device)) {
            throw new IllegalArgumentException("Device already connected.");
        }
        currentHost.addDevice(device);
        hostManagerPort.addDevice(device);
    }

    public void stopParty() {
        if (currentHost != null) {
            hostManagerPort.stopHosting();
            currentHost = null;
        }
    }
}
