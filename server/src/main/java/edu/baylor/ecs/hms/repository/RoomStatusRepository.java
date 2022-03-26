/*
 * Filename: RoomStatusRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.room.RoomStatus;
import edu.baylor.ecs.hms.model.room.RoomStatusName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for RoomStatus data
 *
 * @author Andrew Walker
 */
@Repository
public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {

    /**
     * Find room status by name
     * @param roomName name of a room status
     * @return a RoomStatus by name
     */
    Optional<RoomStatus> findByName(RoomStatusName roomName);
}
