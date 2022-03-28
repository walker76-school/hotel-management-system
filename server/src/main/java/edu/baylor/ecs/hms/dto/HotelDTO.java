package edu.baylor.ecs.hms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A Hotel data transfer object
 *
 * @author Andrew Walker
 */
@Getter
@Setter
@NoArgsConstructor
public class HotelDTO {

    private Long id;
    private String name;
    private String addressLineOne;
    private String addressLineTwo;
    private String city;
    private String state;
    private String zip;
    private Long hotelManagerId;

    public HotelDTO(Long id, String name, String addressLineOne, String addressLineTwo, String city, String state, String zip, Long hotelManagerId) {
        this.id = id;
        this.name = name;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.hotelManagerId = hotelManagerId;
    }

    public HotelDTO(Long id, String name, String addressLineOne, String addressLineTwo, String city, String state, String zip) {
        this.id = id;
        this.name = name;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}