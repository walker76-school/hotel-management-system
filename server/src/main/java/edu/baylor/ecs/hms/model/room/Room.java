/*
 * Filename: Room.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.room;

import edu.baylor.ecs.hms.dto.RoomDTO;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor

@Entity
@Table(name = "rooms", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "roomNumber"
        })
})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long roomNumber;

    @NotNull
    private Long floorNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "room_statuses",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private RoomStatus status = null;

    @OneToMany
    private Set<Reservation> reservations = new HashSet<>();

    @ManyToOne
    private Hotel hotel = null;

    public Room(RoomDTO dto) {
        this.id = dto.getId();
        this.roomNumber = dto.getRoomNumber();
        this.floorNumber = dto.getFloorNumber();
        this.status = dto.getStatus();
    }

    public RoomDTO toDTO() {
        return new RoomDTO(id, roomNumber, floorNumber, status);
    }
}
