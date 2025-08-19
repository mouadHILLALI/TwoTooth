package com.mouad.twotooth.infrastructure.adpater.Host;

import com.mouad.twotooth.core.domain.Device;
import com.mouad.twotooth.core.domain.Host;
import com.mouad.twotooth.core.port.host.HostManagerPort;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HostManagerAdapter implements HostManagerPort {
    private Host currentHost;
    private JmDNS jmDNS;
    private final Set<Device> connectedDevices = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void startHosting(Host host) {
        try {
            this.currentHost = host;
            InetAddress addr = InetAddress.getLocalHost();
            jmDNS = JmDNS.create(addr);

            ServiceInfo serviceInfo = ServiceInfo.create(
                    "_twotooth._udp.local.",
                    host.getName(),
                    host.getPort(),
                    "TwoTooth offline party host"
            );

            jmDNS.registerService(serviceInfo);
            System.out.println("Hosting started: " + host.getName() + " on port " + host.getPort());

            // TODO: Start TCP/UDP servers for guests here

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopHosting() {
        if (jmDNS != null) {
            jmDNS.unregisterAllServices();
            try {
                jmDNS.close();
            } catch (Exception ignored) {
            }
        }
        connectedDevices.clear();
        currentHost = null;
        System.out.println("Hosting stopped.");
    }

    @Override
    public void addDevice(Device device) {
        connectedDevices.add(device);
        System.out.println("Device connected: " + device);
        // TODO: Notify TCP/UDP server about new device
    }

    @Override
    public void removeDevice(Device device) {
        connectedDevices.remove(device);
        System.out.println("Device disconnected: " + device);
        // TODO: Notify TCP/UDP server about device removal
    }
}
