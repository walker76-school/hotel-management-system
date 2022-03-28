package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.HotelDAO;
import edu.baylor.ecs.hms.dto.HotelDTO;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class HotelService implements IService<HotelDTO> {

    @Autowired
    HotelDAO hotelDAO;

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
    public void update(HotelDTO hotelDTO) {
        hotelDAO.save(new Hotel(hotelDTO));
    }

    @Override
    public void delete(HotelDTO hotelDTO) {
        hotelDAO.delete(new Hotel(hotelDTO));
    }

    @Override
    public void deleteById(Long id) {
        hotelDAO.deleteById(id);
    }
}
