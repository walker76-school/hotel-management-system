package edu.baylor.ecs.hms.payload.request.create;

import edu.baylor.ecs.hms.dto.AmenityDTO;
import edu.baylor.ecs.hms.model.amenity.AmenityStatusName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AmenityRequest {

    private String name;
    private String description;
    private AmenityStatusName status;

    public AmenityDTO toDTO() {
        return new AmenityDTO(null, name, description, status);
    }
}