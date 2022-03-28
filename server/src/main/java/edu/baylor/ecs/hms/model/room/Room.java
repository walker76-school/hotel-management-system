/*
 * Filename: Room.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.room;

import edu.baylor.ecs.hms.dto.RoomDTO;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany
    private Set<Reservation> reservations = new HashSet<>();

    public Room(Long roomNumber, Long floorNumber) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
    }

    public Room(Long roomNumber, Long floorNumber, RoomStatus status) {
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.status = status;
    }

    public RoomDTO toDTO() {
        return new RoomDTO(roomNumber, floorNumber, status);
    }
}
