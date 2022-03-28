package edu.baylor.ecs.hms.payload.request.update;

import edu.baylor.ecs.hms.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateRequest {
    private Long id;
    private Long stars;
    private String description;

    public ReviewDTO toDTO() {
        return new ReviewDTO(id, stars, description);
    }
}
