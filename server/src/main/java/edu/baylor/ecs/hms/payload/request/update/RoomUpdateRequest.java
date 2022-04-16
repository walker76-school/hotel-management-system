package edu.baylor.ecs.hms.payload.request.update;

/*
 * Filename: RoomRequest.java
 * Author: Andrew Walker
 */

import edu.baylor.ecs.hms.dto.RoomDTO;
import edu.baylor.ecs.hms.model.room.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A request object to create a room
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomUpdateRequest {
    private Long id;
    private String roomNumber;
    private Long floorNumber;
    private RoomStatus status;

    public RoomDTO toDTO() {
        return new RoomDTO(id, roomNumber, floorNumber, status);
    }
}