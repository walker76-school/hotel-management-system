package edu.baylor.ecs.hms.payload.request.update;

import edu.baylor.ecs.hms.dto.HotelDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HotelUpdateRequest {
    private Long id;
    private String name;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String zip;

    public HotelDTO toDTO() {
        return new HotelDTO(id, name, addressLineOne, addressLineTwo, city, state, zip);
    }
}
