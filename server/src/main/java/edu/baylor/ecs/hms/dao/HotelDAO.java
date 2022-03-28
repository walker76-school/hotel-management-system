package edu.baylor.ecs.hms.dao;

import edu.baylor.ecs.hms.model.hotel.Hotel;
import edu.baylor.ecs.hms.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class HotelDAO implements DAO<Hotel> {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Optional<Hotel> get(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public Collection<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel save(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void update(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }

    @Override
    public void deleteById(Long id) {
        hotelRepository.deleteById(id);
    }
}
