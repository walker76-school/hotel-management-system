package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.ReservationDTO;
import edu.baylor.ecs.hms.payload.request.ReservationRequest;
import edu.baylor.ecs.hms.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for Amenity data
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Returns all amenities
     * @return all amenities
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Collection<ReservationDTO> getAllAmenities() {
        return reservationService.getAll();
    }

    /**
     * Returns all amenities
     * @return all amenities
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ReservationDTO saveAmenity(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.save(reservationRequest.toDTO());
    }

    /**
     * Returns an amenity by id
     * @param id id
     * @return an amenity by id
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ReservationDTO getById(@PathVariable(value = "id") Long id) {
        return reservationService.get(id);
    }

    /**
     * Deletes an amenity by id
     * @param id id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable(value = "id") Long id) {
        reservationService.deleteById(id);
    }
}
