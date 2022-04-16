package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.HotelDAO;
import edu.baylor.ecs.hms.dto.HotelManagerDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import edu.baylor.ecs.hms.model.people.HotelManager;
import edu.baylor.ecs.hms.repository.HotelManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class HotelManagerService {

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private HotelManagerRepository hotelManagerRepository;

    public HotelManagerDTO get(Long id) {
        return hotelManagerRepository.findById(id).map(HotelManager::toDTO).orElse(null);
    }

    public void linkHotelAndManager(Long hotelId, Long managerId) throws Throwable {
        HotelManager manager = hotelManagerRepository.findById(managerId).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("hotelmanager", "id", managerId));
        Hotel hotel = hotelDAO.get(hotelId).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("hotel", "id", hotelId));

        manager.setHotel(hotel);
        hotel.setManager(manager);

        hotelManagerRepository.save(manager);
        hotelDAO.update(hotel);
    }
}
