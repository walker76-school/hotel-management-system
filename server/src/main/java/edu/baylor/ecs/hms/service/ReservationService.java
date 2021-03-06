package edu.baylor.ecs.hms.service;

import edu.baylor.ecs.hms.dao.HotelDAO;
import edu.baylor.ecs.hms.dao.ReservationDAO;
import edu.baylor.ecs.hms.dao.RoomDAO;
import edu.baylor.ecs.hms.dto.ReservationDTO;
import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.people.Customer;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import edu.baylor.ecs.hms.model.room.Room;
import edu.baylor.ecs.hms.payload.response.ReservationContract;
import edu.baylor.ecs.hms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
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

    @Autowired
    private HotelDAO hotelDAO;

    @Override
    public ReservationDTO get(Long id) {
        return reservationDAO.get(id).map(Reservation::toDTO).orElse(null);
    }

    @Override
    public Collection<ReservationDTO> getAll() {
        return reservationDAO.getAll().stream().map(Reservation::toDTO).collect(Collectors.toList());
    }

    public Collection<ReservationContract> getAllForHotelId(Long hotelId) {
        return reservationDAO.getAll().stream().filter(x -> Objects.equals(x.getRoom().getHotel().getId(), hotelId)).map(Reservation::toContract).collect(Collectors.toList());
    }

    @Override
    public ReservationDTO save(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation(reservationDTO);
        Optional<Customer> customerOptional = customerRepository.findByUsername(reservationDTO.getCustomerUsername());
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            Optional<Room> roomOptional = roomDAO.get(reservationDTO.getRoomId());
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
                throw new ResourceNotFoundException("room", "roomId", reservationDTO.getRoomId());
            }
        } else {
            throw new ResourceNotFoundException("user", "username", reservationDTO.getCustomerUsername());
        }
    }

    @Override
    public void update(ReservationDTO reservationDTO) throws Throwable {
        Reservation reservation = reservationDAO.get(reservationDTO.getId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("reservation", "id", reservationDTO.getId()));
        Room room = roomDAO.get(reservationDTO.getRoomId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("room", "id", reservationDTO.getRoomId()));

        reservation.setRoom(room);
        room.getReservations().add(reservation);

        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());

        roomDAO.update(room);
        reservationDAO.update(reservation);
    }

    public void update(ReservationDTO reservationDTO, Long oldRoomId) throws Throwable {
        Reservation reservation = reservationDAO.get(reservationDTO.getId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("reservation", "id", reservationDTO.getId()));
        Room room = roomDAO.get(oldRoomId).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("room", "id", oldRoomId));

        room.getReservations().remove(reservation);
        reservation.setRoom(null);

        roomDAO.update(room);
        reservationDAO.update(reservation);

        update(reservationDTO);
    }

    @Override
    public void delete(ReservationDTO reservationDTO) {
        reservationDAO.delete(new Reservation(reservationDTO));
    }

    @Override
    public void deleteById(Long id) throws Throwable {
        Reservation reservation = reservationDAO.get(id).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("reservation", "id", id));
        Room room = roomDAO.get(reservation.getRoom().getId()).orElseThrow((Supplier<Throwable>) () -> new ResourceNotFoundException("room", "id", reservation.getRoom().getId()));
        Optional<Customer> customerOptional = customerRepository.findById(reservation.getCustomer().getId());
        if(customerOptional.isPresent()) {
            Customer customer = customerOptional.get();

            customer.getReservations().remove(reservation);
            room.getReservations().remove(reservation);
            customerRepository.save(customer);
            roomDAO.update(room);

            reservationDAO.deleteById(id);
        }
    }
}
