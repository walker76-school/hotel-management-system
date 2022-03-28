package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.HotelDTO;
import edu.baylor.ecs.hms.payload.request.HotelRequest;
import edu.baylor.ecs.hms.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for Hotel data
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    /**
     * Returns all hotels
     * @return all hotels
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Collection<HotelDTO> getAll() {
        return hotelService.getAll();
    }

    /**
     * Save a hotel
     * @return a saved hotel
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public HotelDTO save(@RequestBody HotelRequest hotelRequest) {
        return hotelService.save(hotelRequest.toDTO());
    }

    /**
     * Returns a hotel by id
     * @param id id
     * @return a hotel by id
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public HotelDTO getById(@PathVariable(value = "id") Long id) {
        return hotelService.get(id);
    }

    /**
     * Deletes a hotel by id
     * @param id id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable(value = "id") Long id) {
        hotelService.deleteById(id);
    }
}
