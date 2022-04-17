package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.HotelManagerDTO;
import edu.baylor.ecs.hms.dto.ReservationDTO;
import edu.baylor.ecs.hms.payload.request.misc.LinkHotelAndHotelManagerRequest;
import edu.baylor.ecs.hms.service.HotelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for Manager data
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private HotelManagerService managerService;

    @GetMapping("/{managerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public HotelManagerDTO getForId(@PathVariable("managerId") Long managerId) {
        return managerService.get(managerId);
    }

    /**
     * Link amenity and hotel
     */
    @PostMapping("/link")
    @PreAuthorize("hasRole('ADMIN')")
    public HotelManagerDTO link(@RequestBody LinkHotelAndHotelManagerRequest linkRequest) throws Throwable {
        return managerService.linkHotelAndManager(linkRequest.getHotelId(), linkRequest.getManagerId());
    }
}
