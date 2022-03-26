package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.room.RoomDTO;
import edu.baylor.ecs.hms.payload.room.RoomRequest;
import edu.baylor.ecs.hms.service.room.RoomService;
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
    public Collection<RoomDTO> getAllRooms() {
        return roomService.getAll();
    }

    /**
     * Returns all rooms
     * @return all rooms
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public RoomDTO saveRoom(@RequestBody RoomRequest roomRequest) {
        return roomService.save(roomRequest.toDTO());
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
}
