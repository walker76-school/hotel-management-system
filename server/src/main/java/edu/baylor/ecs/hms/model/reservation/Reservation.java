package edu.baylor.ecs.hms.model.reservation;

import edu.baylor.ecs.hms.dto.ReservationDTO;
import edu.baylor.ecs.hms.model.people.Customer;
import edu.baylor.ecs.hms.model.room.Room;
import edu.baylor.ecs.hms.payload.response.ReservationContract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * A Reservation
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Instant startDate;

    @NotNull
    private Instant endDate;

    @ManyToOne
    private Customer customer = null;

    @ManyToOne
    private Room room = null;

    public Reservation(ReservationDTO dto) {
        this.id = dto.getId();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }

    public ReservationDTO toDTO() {
        return new ReservationDTO(id, startDate, endDate, customer.getUsername(), room.getId());
    }

    public ReservationContract toContract() {
        return new ReservationContract(id, startDate, endDate, customer.getUsername(), room.getRoomNumber(), room.getId(), room.getHotel().getName(), room.getHotel().getId());
    }
}