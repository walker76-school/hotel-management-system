/*
 * Filename: Room.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.room;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * A Room
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "rooms", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "roomNumber"
        })
})
public class Room {

    @Id
    @NotNull
    private Long roomNumber;

    @NotNull
    private Long floorNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "room_statuses",
            joinColumns = @JoinColumn(name = "room_number"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private RoomStatus status = null;

    public Room(Long roomNumber, Long floorNumber) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
    }
}
