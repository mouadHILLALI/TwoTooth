package com.mouad.twotooth.core.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DeviceRegistry {
    private final Map<String , Device> devices = new HashMap<>();

    public void addDevice(Device device) {
        devices.put(device.getName(), device);
    }

    public Map<String, Device> getDevices() {
        return Collections.unmodifiableMap(devices);
    }

}