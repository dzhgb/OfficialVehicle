package cn.projectivity.service;

import cn.projectivity.entity.Home;
import cn.projectivity.entity.Room;

import java.util.Optional;

public interface RoomService {
    public Optional<Room> findById(long id);

    public Room findById(String id);

    public Room save(Room room);

    public void delete(Room room);
}