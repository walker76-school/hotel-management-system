/*
 * Filename: RoomDTO.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.dto;

import edu.baylor.ecs.hms.model.room.Room;
import edu.baylor.ecs.hms.model.room.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Room data transfer object
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private String roomNumber;
    private Long floorNumber;
    private RoomStatus status;

    public static RoomDTO fromRoom(Room room) {
        return new RoomDTO(room.getId(), room.getRoomNumber(), room.getFloorNumber(), room.getStatus());
    }
}
