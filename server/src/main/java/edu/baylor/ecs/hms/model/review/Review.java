/*
 * Filename: Review.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.model.review;

import edu.baylor.ecs.hms.dto.ReviewDTO;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A Review
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long stars;

    @NotBlank
    @Size(max = 200)
    private String description;

    @ManyToOne
    private Hotel hotel = null;

    public Review(ReviewDTO dto) {
        this.stars = dto.getStars();
        this.description = dto.getDescription();
    }

    public ReviewDTO toDTO() {
        return new ReviewDTO(id, stars, description);
    }
}