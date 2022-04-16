package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.HotelDAO;
import edu.baylor.ecs.hms.dao.RoomDAO;
import edu.baylor.ecs.hms.dto.HotelDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import edu.baylor.ecs.hms.model.room.Room;
import edu.baylor.ecs.hms.model.room.RoomStatus;
import edu.baylor.ecs.hms.model.room.RoomStatusName;
import edu.baylor.ecs.hms.payload.request.create.CreateScaffoldHotelRequest;
import edu.baylor.ecs.hms.repository.RoomStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class HotelService implements IService<HotelDTO> {

    @Autowired
    HotelDAO hotelDAO;

    @Autowired
    RoomDAO roomDAO;

    @Autowired
    RoomStatusRepository roomStatusRepository;

    @Override
    public HotelDTO get(Long id) {
        return hotelDAO.get(id).map(Hotel::toDTO).orElse(null);
    }

    @Override
    public Collection<HotelDTO> getAll() {
        return hotelDAO.getAll().stream().map(Hotel::toDTO).collect(Collectors.toList());
    }

    @Override
    public HotelDTO save(HotelDTO hotelDTO) {
        return hotelDAO.save(new Hotel(hotelDTO)).toDTO();
    }

    @Override
    public void update(HotelDTO hotelDTO) throws Throwable {
        Hotel hotel = hotelDAO.get(hotelDTO.getId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("hotel", "id", hotelDTO.getId()));

        hotel.setName(hotelDTO.getName());
        hotel.setAddressLineOne(hotelDTO.getAddressLineOne());
        hotel.setAddressLineTwo(hotelDTO.getAddressLineTwo());
        hotel.setCity(hotelDTO.getCity());
        hotel.setState(hotelDTO.getState());
        hotel.setZip(hotelDTO.getZip());

        hotelDAO.update(hotel);
    }

    @Override
    public void delete(HotelDTO hotelDTO) {
        hotelDAO.delete(new Hotel(hotelDTO));
    }

    @Override
    public void deleteById(Long id) {
        hotelDAO.deleteById(id);
    }

    public HotelDTO construct(CreateScaffoldHotelRequest hotelRequest) {
        HotelDTO dto = save(hotelRequest.toDTO());

        Optional<Hotel> hotelOpt = hotelDAO.get(dto.getId());
        if(hotelOpt.isPresent()) {
            Hotel hotel = hotelOpt.get();
            Optional<RoomStatus> pendingStatusOpt = roomStatusRepository.findByName(RoomStatusName.ROOM_STATUS_VACANT);
            if(pendingStatusOpt.isPresent()){
                RoomStatus pendingStatus = pendingStatusOpt.get();
                Set<Room> rooms = new HashSet<>();
                for (int i = 0; i < hotelRequest.getNumFloors(); i++) {
                    for (int j = 0; j < hotelRequest.getNumRooms(); j++) {
                        String roomNumber = "" + hotel.getId() + i + "00" + j;
                        Room room = new Room(roomNumber, (long) i, pendingStatus, hotel);
                        rooms.add(room);
                    }
                }
                hotel.setRooms(rooms);
                roomDAO.saveAll(rooms);
                hotelDAO.update(hotel);

                return hotel.toDTO();
            }
        }
        return null;
    }
}
