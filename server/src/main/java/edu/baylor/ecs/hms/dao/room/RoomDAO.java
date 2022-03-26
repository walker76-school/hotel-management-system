package edu.baylor.ecs.hms.dao.room;

import edu.baylor.ecs.hms.dao.DAO;
import edu.baylor.ecs.hms.model.room.Room;
import edu.baylor.ecs.hms.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class RoomDAO implements DAO<Room> {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public Optional<Room> get(Long roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber);
    }

    @Override
    public Collection<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void update(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void delete(Room room) {
        roomRepository.delete(room);
    }
}
