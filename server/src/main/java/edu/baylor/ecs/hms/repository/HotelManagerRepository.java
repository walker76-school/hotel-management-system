/*
 * Filename: CustomerRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.people.HotelManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Hotel Manager data
 *
 * @author Andrew Walker
 */
@Repository
public interface HotelManagerRepository extends JpaRepository<HotelManager, Long> {

}
