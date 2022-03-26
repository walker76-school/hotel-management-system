/*
 * Filename: AmenityStatus.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.amenity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * A status of an Amenity
 */
@AllArgsConstructor
@Data
@NoArgsConstructor

@Entity
@Table(name = "amenity_status")
public class AmenityStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private AmenityStatusName name;

}
