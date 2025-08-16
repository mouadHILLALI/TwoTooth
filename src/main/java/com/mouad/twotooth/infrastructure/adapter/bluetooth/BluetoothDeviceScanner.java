package com.mouad.twotooth.infrastructure.adapter.bluetooth;

import com.mouad.twotooth.core.domain.BluetoothDevice;
import com.mouad.twotooth.core.port.output.DeviceScannerPort;

import javax.bluetooth.*;
import java.util.HashSet;
import java.util.Set;

public class BluetoothDeviceScanner implements DeviceScannerPort, DiscoveryListener {

    private final Set<BluetoothDevice> foundDevices = new HashSet<>();
    private final Object inquiryCompletedEvent = new Object();

    @Override
    public Set<BluetoothDevice> scanAvailableDevices() throws BluetoothStateException, InterruptedException {
        try {
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent discoveryAgent = localDevice.getDiscoveryAgent();
            synchronized (inquiryCompletedEvent) {
                boolean started = discoveryAgent.startInquiry(DiscoveryAgent.GIAC, this);
                if (started) inquiryCompletedEvent.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundDevices;
    }

    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        try {
            String address = remoteDevice.getBluetoothAddress();
            String name = remoteDevice.getFriendlyName(false);
            foundDevices.add(new BluetoothDevice(address, name));
        } catch (Exception e) {
            foundDevices.add(new BluetoothDevice(remoteDevice.getBluetoothAddress(), "unknown"));
        }

    }

    @Override
    public void servicesDiscovered(int i, ServiceRecord[] serviceRecords) {}

    @Override
    public void serviceSearchCompleted(int i, int i1) {}

    @Override
    public void inquiryCompleted(int i) {
        synchronized (inquiryCompletedEvent) {
            inquiryCompletedEvent.notifyAll();
        }
    }
}