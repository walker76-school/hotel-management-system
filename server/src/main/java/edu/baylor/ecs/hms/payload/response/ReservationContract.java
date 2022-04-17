package edu.baylor.ecs.hms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationContract {
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private String customerUsername;
    private String roomNumber;
    private Long roomId;
    private String hotelName;
    private Long hotelId;
}
