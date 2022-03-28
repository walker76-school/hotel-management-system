package edu.baylor.ecs.hms.model.hotel;

import edu.baylor.ecs.hms.dto.HotelDTO;
import edu.baylor.ecs.hms.model.amenity.Amenity;
import edu.baylor.ecs.hms.model.people.HotelManager;
import edu.baylor.ecs.hms.model.review.Review;
import edu.baylor.ecs.hms.model.room.Room;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * A Reservation
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String addressLineOne;

    @Size(max = 100)
    private String addressLineTwo;

    @NotBlank
    @Size(max = 20)
    private String city;

    @NotBlank
    @Size(max = 15)
    private String state;

    @NotBlank
    @Size(max = 7)
    private String zip;

    @OneToOne
    private HotelManager manager = null;

    @OneToMany
    private Set<Room> rooms = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities = new HashSet<>();

    @OneToMany
    private Set<Review> reviews = new HashSet<>();

    public Hotel(HotelDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.addressLineOne = dto.getAddressLineOne();
        this.addressLineTwo = dto.getAddressLineTwo();
        this.city = dto.getCity();
        this.state = dto.getState();
        this.zip = dto.getZip();
    }

    public HotelDTO toDTO() {
        Long managerId = manager != null ? manager.getId() : null;
        return new HotelDTO(id, name, addressLineOne, addressLineTwo, city, state, zip, managerId);
    }
}