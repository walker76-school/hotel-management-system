package edu.baylor.ecs.hms.dao;

import edu.baylor.ecs.hms.model.amenity.Amenity;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import edu.baylor.ecs.hms.repository.AmenityRepository;
import edu.baylor.ecs.hms.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ReservationDAO implements DAO<Reservation> {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Optional<Reservation> get(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Collection<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
}
