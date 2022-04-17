package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.ReservationDTO;
import edu.baylor.ecs.hms.payload.request.create.ReservationRequest;
import edu.baylor.ecs.hms.payload.request.update.ReservationUpdateRequest;
import edu.baylor.ecs.hms.payload.response.ReservationContract;
import edu.baylor.ecs.hms.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for Reservation data
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Returns all reservations
     * @return all reservations
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Collection<ReservationDTO> getAll() {
        return reservationService.getAll();
    }

    /**
     * Returns all reservations
     * @return all reservations
     */
    @GetMapping("/byHotelId/{hotelId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Collection<ReservationContract> getAllForHotelId(@PathVariable("hotelId") Long hotelId) {
        return reservationService.getAllForHotelId(hotelId);
    }

    /**
     * Saves a reservation
     * @return a reservation
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ReservationDTO save(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.save(reservationRequest.toDTO());
    }

    /**
     * Update a reservation
     */
    @PutMapping("/")
    @PreAuthorize("hasRole('USER')")
    public void update(@RequestBody ReservationUpdateRequest reservationRequest) throws Throwable {
        reservationService.update(reservationRequest.toDTO());
    }

    /**
     * Returns a reservation by id
     * @param id id
     * @return a reservation by id
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ReservationDTO getById(@PathVariable(value = "id") Long id) {
        return reservationService.get(id);
    }

    /**
     * Deletes a reservation by id
     * @param id id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteById(@PathVariable(value = "id") Long id) {
        reservationService.deleteById(id);
    }
}
