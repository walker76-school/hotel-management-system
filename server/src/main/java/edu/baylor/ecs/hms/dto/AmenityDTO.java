/*
 * Filename: AmenityDTO.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.dto;

import edu.baylor.ecs.hms.model.amenity.Amenity;
import edu.baylor.ecs.hms.model.amenity.AmenityStatus;
import edu.baylor.ecs.hms.model.room.RoomStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * An Amenity data transfer object
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor
public class AmenityDTO {

    private Long id;
    private String name;
    private String description;
    private AmenityStatus status;

    public AmenityDTO(Long id, String name, String description, AmenityStatus status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public static AmenityDTO fromAmenity(Amenity amenity) {
        return new AmenityDTO(amenity.getId(), amenity.getName(), amenity.getDescription(), amenity.getStatus());
    }
}
