package com.mouad.twotooth.core.port.device;

import com.mouad.twotooth.core.domain.Device;

import java.net.UnknownHostException;
import java.util.Set;

public interface DeviceScannerPort { 
    Set<Device> discoverDevices() throws UnknownHostException;
}