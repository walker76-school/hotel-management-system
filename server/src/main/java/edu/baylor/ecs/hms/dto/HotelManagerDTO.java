package edu.baylor.ecs.hms.dto;

import lombok.Data;

@Data
public class HotelManagerDTO {
    private Long managerId;
    private Long hotelId;

    public HotelManagerDTO(Long id, Long hotelId) {
        this.managerId = id;
        this.hotelId = hotelId;
    }
}
