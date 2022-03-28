package edu.baylor.ecs.hms.model.people;

import edu.baylor.ecs.hms.model.auth.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CUST")
public class Customer extends User {

    public Customer(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

}
