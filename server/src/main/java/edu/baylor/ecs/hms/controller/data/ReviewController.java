package edu.baylor.ecs.hms.controller.data;

import edu.baylor.ecs.hms.dto.ReviewDTO;
import edu.baylor.ecs.hms.payload.request.create.ReviewRequest;
import edu.baylor.ecs.hms.payload.request.update.ReviewUpdateRequest;
import edu.baylor.ecs.hms.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Controller for Review data
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Returns all review
     * @return all review
     */
    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public Collection<ReviewDTO> getAll() {
        return reviewService.getAll();
    }

    /**
     * Saves a review
     * @return saved review
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ReviewDTO save(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.save(reviewRequest.toDTO());
    }

    /**
     * Saves a review
     * @return saved review
     */
    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public void update(@RequestBody ReviewUpdateRequest reviewRequest) throws Throwable {
        reviewService.update(reviewRequest.toDTO());
    }

    /**
     * Returns an review by id
     * @param id id
     * @return an review by id
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ReviewDTO getById(@PathVariable(value = "id") Long id) {
        return reviewService.get(id);
    }

    /**
     * Deletes an review by id
     * @param id id
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable(value = "id") Long id) {
        reviewService.deleteById(id);
    }
}
