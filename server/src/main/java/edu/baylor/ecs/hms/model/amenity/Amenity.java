/*
 * Filename: Amenity.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.amenity;

import edu.baylor.ecs.hms.dto.AmenityDTO;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 250)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "amenity_statuses",
            joinColumns = @JoinColumn(name = "amenity_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private AmenityStatus status = null;

    @ManyToMany
    private Set<Hotel> hotels = new HashSet<>();

    public Amenity(String name, String description, AmenityStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public AmenityDTO toDTO() {
        return new AmenityDTO(id, name, description, status.getName());
    }
}
