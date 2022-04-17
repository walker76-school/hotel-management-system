package edu.baylor.ecs.hms.payload.request.create;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScaffoldHotelRequest extends HotelRequest {

    private Long numFloors;
    private Long numRooms;

    public CreateScaffoldHotelRequest(String name, String addressLineOne, String addressLineTwo, String city, String state, String zip, Long numFloors, Long numRooms) {
        super(name, addressLineOne, addressLineTwo, city, state, zip);
        this.numFloors = numFloors;
        this.numRooms = numRooms;
    }
}
