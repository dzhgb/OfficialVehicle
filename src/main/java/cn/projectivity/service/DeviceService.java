package cn.projectivity.service;

import cn.projectivity.entity.Device;

import java.util.Optional;

public interface DeviceService {
    public Optional<Device> findById(long id);

    public Device save(Device device);

    public void delete(Device device);
}