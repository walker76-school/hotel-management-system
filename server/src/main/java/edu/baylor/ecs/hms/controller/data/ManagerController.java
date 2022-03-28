package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.payload.request.misc.LinkHotelAndHotelManagerRequest;
import edu.baylor.ecs.hms.service.HotelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
