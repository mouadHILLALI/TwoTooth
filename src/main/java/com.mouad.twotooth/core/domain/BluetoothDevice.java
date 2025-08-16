package main.java.com.mouad.twotooth.core.domain;

public class BluetoothDevice {

    private final String address;
    private final String name;

    public BluetoothDevice(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

}
