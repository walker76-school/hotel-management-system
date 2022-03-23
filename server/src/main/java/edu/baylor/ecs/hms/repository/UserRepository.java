/*
 * Filename: UserRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for User data
 *
 * @author Andrew Walker
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find by username
     * @param username username
     * @return a User by username
     */
    Optional<User> findByUsername(String username);

    /**
     * Returns if a username is taken
     * @param username username
     * @return if a username is taken
     */
    Boolean existsByUsername(String username);
}
