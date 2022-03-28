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
     * Returns a room by roomId
     * @param roomId roomId
     * @return a room by roomIdr
     */
    @GetMapping("/{roomId}")
    @PreAuthorize("hasRole('USER')")
    public RoomDTO get(@PathVariable(value = "roomId") Long roomId) {
        return roomService.get(roomId);
    }

    /**
     * Deletes a room by room number
     * @param roomId roomId
     */
    @DeleteMapping("/{roomId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable(value = "roomId") Long roomId) {
        roomService.deleteById(roomId);
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
