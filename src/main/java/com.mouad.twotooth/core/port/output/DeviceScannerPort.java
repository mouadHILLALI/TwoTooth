package main.java.com.mouad.twotooth.core.port.output;

import main.java.com.mouad.twotooth.core.domain.BluetoothDevice;

import javax.bluetooth.BluetoothStateException;
import java.util.Set;

public interface DeviceScannerPort { 
    Set<BluetoothDevice> scanAvailableDevices() throws BluetoothStateException, InterruptedException;
}