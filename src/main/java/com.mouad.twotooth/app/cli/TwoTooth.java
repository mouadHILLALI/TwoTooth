package com.mouad.twotooth.app.cli;

import com.mouad.twotooth.core.port.output.DeviceScannerPort;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TwoTooth {
    private static DeviceScannerPort deviceScannerPort;
    public static void main(String[] args) throws UnknownHostException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TwoTooth!");
        System.out.println("Select mode: 1 = Host, 2 = Guest");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            deviceScannerPort.discoverDevices();
        } else if (choice == 2) {
            System.out.printf("Host: %s%n", InetAddress.getLocalHost().getHostName());
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }
}
