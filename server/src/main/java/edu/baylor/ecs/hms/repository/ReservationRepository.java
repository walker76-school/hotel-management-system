/*
 * Filename: ReservationRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Reservation data
 *
 * @author Andrew Walker
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Returns if an Reservation id is taken
     * @param id id
     * @return if an id is taken
     */
    boolean existsById(@NonNull Long id);

}
