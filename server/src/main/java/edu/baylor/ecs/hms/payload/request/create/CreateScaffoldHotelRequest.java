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
}
