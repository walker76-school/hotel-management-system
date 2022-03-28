package edu.baylor.ecs.hms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private Long id;
    private Instant startDate;
    private Instant endDate;
    private Long customerNumber;
    private Long roomNumber;
}
