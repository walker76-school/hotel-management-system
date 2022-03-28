package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.RoomDTO;
import edu.baylor.ecs.hms.payload.request.create.RoomRequest;
import edu.baylor.ecs.hms.payload.request.misc.LinkHotelAndAmenityRequest;
import edu.baylor.ecs.hms.payload.request.misc.LinkHotelAndRoomRequest;
import edu.baylor.ecs.hms.payload.request.update.RoomUpdateRequest;
import edu.baylor.ecs.hms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for Room data
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Returns all rooms
     * @return all rooms
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Collection<RoomDTO> getAll() {
        return roomService.getAll();
    }

    /**
     * Saves a room
     * @return saved room
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDTO save(@RequestBody RoomRequest roomRequest) {
        return roomService.save(roomRequest.toDTO());
    }

    /**
     * Saves a room
     * @return saved room
     */
    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody RoomUpdateRequest roomRequest) throws Throwable {
        roomService.update(roomRequest.toDTO());
    }

    /**
     * Returns a room by room number
     * @param roomNumber roomNumber
     * @return a room by room number
     */
    @GetMapping("/{roomId}")
    @PreAuthorize("hasRole('USER')")
    public RoomDTO getRoomByRoomNumber(@PathVariable(value = "roomNumber") Long roomNumber) {
        return roomService.get(roomNumber);
    }

    /**
     * Deletes a room by room number
     * @param roomNumber roomNumber
     */
    @DeleteMapping("/{roomNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteByRoomNumber(@PathVariable(value = "roomNumber") Long roomNumber) {
        roomService.deleteById(roomNumber);
    }

    /**
     * Link room and hotel
     */
    @PostMapping("/link")
    @PreAuthorize("hasRole('ADMIN')")
    public void link(@RequestBody LinkHotelAndRoomRequest request) throws Throwable {
        roomService.linkHotelAndRoom(request.getHotelId(), request.getRoomId());
    }
}
