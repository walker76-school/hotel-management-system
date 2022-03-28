package edu.baylor.ecs.hms.payload.request.update;

import edu.baylor.ecs.hms.dto.AmenityDTO;
import edu.baylor.ecs.hms.model.amenity.AmenityStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmenityUpdateRequest {

    private Long id;
    private String name;
    private String description;
    private AmenityStatus status;

    public AmenityDTO toDTO() {
        return new AmenityDTO(id, name, description, status);
    }
}