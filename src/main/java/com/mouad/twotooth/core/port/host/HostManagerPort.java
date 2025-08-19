package com.mouad.twotooth.core.port.host;

import com.mouad.twotooth.core.domain.Device;
import com.mouad.twotooth.core.domain.Host;

public interface HostManagerPort {
    void startHosting(Host host);

    void stopHosting();

    void addDevice(Device device);

    void removeDevice(Device device);
}
