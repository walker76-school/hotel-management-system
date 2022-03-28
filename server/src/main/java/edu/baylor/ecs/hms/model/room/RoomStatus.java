/*
 * Filename: Role.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * A status of a Room
 */
@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "room_status")
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoomStatusName name;

}
