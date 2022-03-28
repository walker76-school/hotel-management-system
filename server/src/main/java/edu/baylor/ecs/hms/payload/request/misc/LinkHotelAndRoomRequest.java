package edu.baylor.ecs.hms.payload.request.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkHotelAndRoomRequest {
    private Long hotelId;
    private Long roomId;
}
