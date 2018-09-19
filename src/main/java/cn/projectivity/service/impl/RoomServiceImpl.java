package cn.projectivity.service.impl;

import cn.projectivity.entity.Room;
import cn.projectivity.repository.RoomRepository;
import cn.projectivity.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room findById(String id) {
        if(id != null && !"".equals(id)){
            Optional<Room> room = roomRepository.findById(Long.valueOf(id));
            if(room.isPresent()){
                return room.get();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public Optional<Room> findById(long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }
}