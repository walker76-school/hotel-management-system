package edu.baylor.ecs.hms.model.people;

import edu.baylor.ecs.hms.model.auth.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("HOTEL")
public class HotelManager extends User {
}
