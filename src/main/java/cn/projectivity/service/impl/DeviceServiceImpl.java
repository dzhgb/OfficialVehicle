package cn.projectivity.service.impl;

import cn.projectivity.entity.Device;
import cn.projectivity.repository.DeviceRepository;
import cn.projectivity.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public Optional<Device> findById(long id) {
        return deviceRepository.findById(id);
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public void delete(Device device) {
        deviceRepository.delete(device);
    }
}