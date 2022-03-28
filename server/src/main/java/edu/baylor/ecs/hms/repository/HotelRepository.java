/*
 * Filename: HotelRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Hotel data
 *
 * @author Andrew Walker
 */
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}