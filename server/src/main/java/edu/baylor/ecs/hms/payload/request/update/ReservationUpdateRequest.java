package edu.baylor.ecs.hms.payload.request.update;

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
public class ReservationUpdateRequest {
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private Long customerNumber;
    private Long roomNumber;

    public ReservationDTO toDTO() {
        return new ReservationDTO(id, startDate, endDate, customerNumber, roomNumber);
    }
}
