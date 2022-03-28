/*
 * Filename: ReviewRepository.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.repository;

import edu.baylor.ecs.hms.model.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for Review data
 *
 * @author Andrew Walker
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}