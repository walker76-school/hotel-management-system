package edu.baylor.ecs.hms.payload.request;

import edu.baylor.ecs.hms.dto.AmenityDTO;
import edu.baylor.ecs.hms.model.amenity.AmenityStatus;

public class AmenityRequest {

    private String name;
    private String description;
    private AmenityStatus status;

    public AmenityRequest(String name, String description, AmenityStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public AmenityDTO toDTO() {
        return new AmenityDTO(null, name, description, status);
    }
}