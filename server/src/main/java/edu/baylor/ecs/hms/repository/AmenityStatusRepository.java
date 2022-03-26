/*
 * Filename: AmenityStatusRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.amenity.AmenityStatus;
import edu.baylor.ecs.hms.model.amenity.AmenityStatusName;
import edu.baylor.ecs.hms.model.room.RoomStatus;
import edu.baylor.ecs.hms.model.room.RoomStatusName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for AmenityStatus data
 *
 * @author Andrew Walker
 */
@Repository
public interface AmenityStatusRepository extends JpaRepository<AmenityStatus, Long> {

    /**
     * Find an amenity status by name
     * @param amenityName name of an amenity status
     * @return an AmenityStatus by name
     */
    Optional<AmenityStatus> findByName(AmenityStatusName amenityName);
}
