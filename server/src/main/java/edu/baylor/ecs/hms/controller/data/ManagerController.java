package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.HotelDTO;
import edu.baylor.ecs.hms.payload.request.create.HotelRequest;
import edu.baylor.ecs.hms.payload.request.misc.LinkHotelAndAmenityRequest;
import edu.baylor.ecs.hms.payload.request.misc.LinkHotelAndHotelManagerRequest;
import edu.baylor.ecs.hms.payload.request.update.HotelUpdateRequest;
import edu.baylor.ecs.hms.service.HotelManagerService;
import edu.baylor.ecs.hms.service.HotelService;
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

    /**
     * Link amenity and hotel
     */
    @PostMapping("/link")
    @PreAuthorize("hasRole('ADMIN')")
    public void link(@RequestBody LinkHotelAndHotelManagerRequest linkRequest) throws Throwable {
        managerService.linkHotelAndManager(linkRequest.getHotelId(), linkRequest.getManagerId());
    }
}