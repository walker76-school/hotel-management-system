package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.ReservationDAO;
import edu.baylor.ecs.hms.dao.RoomDAO;
import edu.baylor.ecs.hms.dto.ReservationDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import edu.baylor.ecs.hms.model.people.Customer;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import edu.baylor.ecs.hms.model.room.Room;
import edu.baylor.ecs.hms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class ReservationService implements IService<ReservationDTO> {

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomDAO roomDAO;

    @Override
    public ReservationDTO get(Long id) {
        return reservationDAO.get(id).map(Reservation::toDTO).orElse(null);
    }

    @Override
    public Collection<ReservationDTO> getAll() {
        return reservationDAO.getAll().stream().map(Reservation::toDTO).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation(reservationDTO);
        Optional<Customer> customerOptional = customerRepository.findById(reservationDTO.getCustomerNumber());
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            Optional<Room> roomOptional = roomDAO.get(reservationDTO.getRoomNumber());
            if(roomOptional.isPresent()) {
                Room room = roomOptional.get();
                reservation.setRoom(room);
                reservation.setCustomer(customer);
                reservationDAO.save(reservation);

                room.getReservations().add(reservation);
                customer.getReservations().add(reservation);
                roomDAO.update(room);
                customerRepository.save(customer);

                return reservation.toDTO();

            } else {
                throw new ResourceNotFoundException("room", "roomNumber", reservationDTO.getRoomNumber());
            }
        } else {
            throw new ResourceNotFoundException("user", "customerNumber", reservationDTO.getCustomerNumber());
        }
    }

    @Override
    public void update(ReservationDTO reservationDTO) throws Throwable {
        Reservation reservation = reservationDAO.get(reservationDTO.getId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("reservation", "id", reservationDTO.getId()));
        Room room = roomDAO.get(reservationDTO.getRoomNumber()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("room", "id", reservationDTO.getRoomNumber()));

        room.getReservations().remove(reservation);
        reservation.setRoom(room);
        room.getReservations().add(reservation);

        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());

        reservationDAO.update(reservation);
        roomDAO.update(room);
    }

    @Override
    public void delete(ReservationDTO reservationDTO) {
        reservationDAO.delete(new Reservation(reservationDTO));
    }

    @Override
    public void deleteById(Long id) {
        reservationDAO.deleteById(id);
    }
}
