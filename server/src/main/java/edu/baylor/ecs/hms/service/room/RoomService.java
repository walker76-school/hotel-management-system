package edu.baylor.ecs.hms.service.room;

import edu.baylor.ecs.hms.dao.room.RoomDAO;
import edu.baylor.ecs.hms.dto.room.RoomDTO;
import edu.baylor.ecs.hms.model.room.Room;
import edu.baylor.ecs.hms.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RoomService implements IService<RoomDTO> {

    @Autowired
    private RoomDAO roomDAO;

    @Override
    public RoomDTO get(Long id) {
        return roomDAO.get(id).map(Room::toDTO).orElse(null);
    }

    @Override
    public Collection<RoomDTO> getAll() {
        return roomDAO.getAll().stream().map(Room::toDTO).collect(Collectors.toList());
    }

    @Override
    public RoomDTO save(RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getRoomNumber(), roomDTO.getFloorNumber(), roomDTO.getStatus());
        return roomDAO.save(room).toDTO();
    }

    @Override
    public void update(RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getRoomNumber(), roomDTO.getFloorNumber(), roomDTO.getStatus());
        roomDAO.save(room).toDTO();
    }

    @Override
    public void delete(RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getRoomNumber(), roomDTO.getFloorNumber(), roomDTO.getStatus());
        roomDAO.delete(room);
    }

    @Override
    public void deleteById(Long id) {
        roomDAO.deleteById(id);
    }
}
