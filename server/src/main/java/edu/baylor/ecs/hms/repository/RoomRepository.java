/*
 * Filename: RoomRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Room data
 *
 * @author Andrew Walker
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Find by roomId
     * @param roomId roomId
     * @return a Room by roomId
     */
    Optional<Room> findByRoomId(Long roomId);

    /**
     * Returns if a roomId is taken
     * @param roomId roomId
     * @return if a roomId is taken
     */
    Boolean existsByRoomId(Long roomId);
}
