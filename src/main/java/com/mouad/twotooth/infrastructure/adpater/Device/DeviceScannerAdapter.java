package com.mouad.twotooth.infrastructure.adpater.Device;


import com.mouad.twotooth.core.domain.Device;
import com.mouad.twotooth.core.port.device.DeviceScannerPort;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DeviceScannerAdapter implements DeviceScannerPort {

    private final Set<Device> foundDevices = Collections.synchronizedSet(new HashSet<Device>());

    @Override
    public Set<Device> discoverDevices() throws UnknownHostException {
        try {
            InetAddress address = InetAddress.getLocalHost();
            JmDNS jmDNS = JmDNS.create(address);
            jmDNS.addServiceListener("_twotooth._udp.local", new ServiceListener() {

                @Override
                public void serviceAdded(ServiceEvent event) {
                    // Triggered when a new service appears on the network
                    System.out.println("Service added: " + event.getName());
                }

                @Override
                public void serviceRemoved(ServiceEvent serviceEvent) {
                    System.out.println("Service removed: " + serviceEvent.getName());
                }

                @Override
                public void serviceResolved(ServiceEvent serviceEvent) {
                    ServiceInfo info = serviceEvent.getInfo();
                    Device device = new Device(info.getHostAddresses()[0], info.getName(), info.getPort());
                    foundDevices.add(device);
                    System.out.println("Device added: " + device);
                }
            });
            Thread.sleep(5000);
            jmDNS.close();
            return foundDevices;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
}

