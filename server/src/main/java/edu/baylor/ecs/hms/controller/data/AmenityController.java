package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.amenity.AmenityDTO;
import edu.baylor.ecs.hms.payload.amenity.AmenityRequest;
import edu.baylor.ecs.hms.service.amenity.AmenityService;
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
@RequestMapping("/api/amenity")
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    /**
     * Returns all amenities
     * @return all amenities
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Collection<AmenityDTO> getAllAmenities() {
        return amenityService.getAll();
    }

    /**
     * Returns all amenities
     * @return all amenities
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public AmenityDTO saveAmenity(@RequestBody AmenityRequest amenityRequest) {
        return amenityService.save(amenityRequest.toDTO());
    }

    /**
     * Returns an amenity by id
     * @param id id
     * @return an amenity by id
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public AmenityDTO getAmenityById(@PathVariable(value = "id") Long id) {
        return amenityService.get(id);
    }

    /**
     * Deletes an amenity by id
     * @param id id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable(value = "id") Long id) {
        amenityService.deleteById(id);
    }
}
