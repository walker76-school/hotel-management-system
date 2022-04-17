package edu.baylor.ecs.hms.model.people;

import edu.baylor.ecs.hms.model.auth.User;
import edu.baylor.ecs.hms.model.reservation.Reservation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@DiscriminatorValue("CUST")
public class Customer extends User {

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    public Customer(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public Customer() {

    }
}
