package com.mouad.twotooth.cli;

import com.mouad.twotooth.core.port.device.DeviceScannerPort;
import com.mouad.twotooth.core.usecases.host.HostUseCases;
import com.mouad.twotooth.infrastructure.adpater.Host.HostManagerAdapter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TwoTooth {
    private static DeviceScannerPort deviceScannerPort;
    private static HostUseCases hostUseCases = new HostUseCases(new HostManagerAdapter());

    public static void main(String[] args) throws UnknownHostException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TwoTooth!");
        System.out.println("Select mode: 1 = Host, 2 = Guest");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice == 1) {
            deviceScannerPort.discoverDevices();
        } else if (choice == 2) {
            CLI cli = new CLI() {
                @Override
                public void startHostParty() throws UnknownHostException {
                    hostUseCases.startParty("twotooth", InetAddress.getLocalHost().getHostAddress(), 33);
                }
            };

            cli.startHostParty();
        } else {
            System.out.println("Invalid choice. Exiting.");
        }

        scanner.close();
    }
}

interface CLI {
    void startHostParty() throws UnknownHostException;
}
