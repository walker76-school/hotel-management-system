package edu.baylor.ecs.hms.payload.request;

import edu.baylor.ecs.hms.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {
    private Long stars;
    private String description;

    public ReviewDTO toDTO() {
        return new ReviewDTO(null, stars, description);
    }
}
