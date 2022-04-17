/*
 * Filename: CustomerRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.people.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Customer data
 *
 * @author Andrew Walker
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
