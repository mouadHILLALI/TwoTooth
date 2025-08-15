package com.mouad.twotooth.core.port.output;

import java.util.List;

public interface DeviceScannerPort { 
    List<BluetoothDevice> scanAvailableDevices();
}