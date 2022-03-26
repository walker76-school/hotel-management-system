/*
 * Filename: Amenity.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.amenity;

import edu.baylor.ecs.hms.dto.amenity.AmenityDTO;
import edu.baylor.ecs.hms.model.room.RoomStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * An Amenity entity
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "amenities")
public class Amenity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 250)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "amenity_statuses",
            joinColumns = @JoinColumn(name = "amenity_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private RoomStatus status = null;

    public Amenity(String name, String description, RoomStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public Amenity(AmenityDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.status = dto.getStatus();
    }

    public AmenityDTO toDTO() {
        return new AmenityDTO(id, name, description, status);
    }
}
