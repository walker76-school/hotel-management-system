package edu.baylor.ecs.hms.payload.request.create;

import edu.baylor.ecs.hms.dto.ReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {
    private Instant startDate;
    private Instant endDate;
    private String customerUsername;
    private Long roomId;

    public ReservationDTO toDTO() {
        return new ReservationDTO(null, startDate, endDate, customerUsername, roomId);
    }
}
