package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.HotelDAO;
import edu.baylor.ecs.hms.dao.RoomDAO;
import edu.baylor.ecs.hms.dto.RoomDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import edu.baylor.ecs.hms.model.room.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class RoomService implements IService<RoomDTO> {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private HotelDAO hotelDAO;

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
        return roomDAO.save(new Room(roomDTO)).toDTO();
    }

    @Override
    public void update(RoomDTO roomDTO) throws Throwable {
        Room room = roomDAO.get(roomDTO.getId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("room", "id", roomDTO.getId()));

        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setFloorNumber(roomDTO.getFloorNumber());
        room.setStatus(roomDTO.getStatus());

        roomDAO.update(room);
    }

    @Override
    public void delete(RoomDTO roomDTO) {
        roomDAO.delete(new Room(roomDTO));
    }

    @Override
    public void deleteById(Long id) {
        roomDAO.deleteById(id);
    }

    public void linkHotelAndRoom(Long hotelId, Long roomId) throws Throwable {
        Room room = roomDAO.get(roomId).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("room", "id", roomId));
        Hotel hotel = hotelDAO.get(hotelId).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("hotel", "id", hotelId));

        room.setHotel(hotel);
        hotel.getRooms().add(room);

        roomDAO.update(room);
        hotelDAO.update(hotel);
    }
}
