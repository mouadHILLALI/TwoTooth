package com.mouad.twotooth.core.port.output;

import com.mouad.twotooth.core.domain.Device;

import javax.bluetooth.BluetoothStateException;
import java.net.UnknownHostException;
import java.util.Set;

public interface DeviceScannerPort { 
    Set<Device> discoverDevices() throws UnknownHostException;
}