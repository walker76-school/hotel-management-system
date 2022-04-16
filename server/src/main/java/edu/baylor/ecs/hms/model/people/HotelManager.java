package edu.baylor.ecs.hms.model.people;

import edu.baylor.ecs.hms.dto.HotelManagerDTO;
import edu.baylor.ecs.hms.model.auth.User;
import edu.baylor.ecs.hms.model.hotel.Hotel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter

@Entity
@DiscriminatorValue("HOTEL")
public class HotelManager extends User {

    @OneToOne
    private Hotel hotel = null;

    public Hotel getHotel() {
        return hotel;
    }

    public HotelManagerDTO toDTO() {
        if(getHotel() == null) {
            return new HotelManagerDTO(getId(), null);
        }
        return new HotelManagerDTO(getId(), getHotel().getId());
    }
}
