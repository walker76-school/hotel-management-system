/*
 * Filename: AmenityRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.amenity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Amenity data
 *
 * @author Andrew Walker
 */
@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    /**
     * Find an Amenity by id
     * @param id id
     * @return an Amenity by id
     */
    @NonNull
    Optional<Amenity> findById(@NonNull Long id);

    /**
     * Returns if an Amenity id is taken
     * @param id id
     * @return if an id is taken
     */
    boolean existsById(@NonNull Long id);

}
