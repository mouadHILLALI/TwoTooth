package main.java.com.mouad.twotooth.core.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeviceRegistry {
    private final Map<String , BluetoothDevice> devices = new HashMap<>();

    public void addDevice(BluetoothDevice device) {
        devices.put(device.getAddress(), device);
    }

    public Map<String, BluetoothDevice> getDevices() {
        return Collections.unmodifiableMap(devices);
    }

}