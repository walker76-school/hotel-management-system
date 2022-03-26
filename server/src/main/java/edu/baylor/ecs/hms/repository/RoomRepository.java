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
     * @param roomNumber roomNumber
     * @return a Room by roomId
     */
    Optional<Room> findByRoomNumber(Long roomNumber);

    /**
     * Returns if a roomId is taken
     * @param roomNumber roomNumber
     * @return if a roomId is taken
     */
    Boolean existsByRoomNumber(Long roomNumber);
}
