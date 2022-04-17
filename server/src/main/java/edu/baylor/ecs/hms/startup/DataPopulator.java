/*
 * Filename: DataPopulator.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.startup;

import edu.baylor.ecs.hms.exception.AppException;
import edu.baylor.ecs.hms.model.auth.Role;
import edu.baylor.ecs.hms.model.auth.RoleName;
import edu.baylor.ecs.hms.model.auth.User;
import edu.baylor.ecs.hms.model.people.Customer;
import edu.baylor.ecs.hms.model.people.HotelManager;
import edu.baylor.ecs.hms.payload.request.create.CreateScaffoldHotelRequest;
import edu.baylor.ecs.hms.repository.CustomerRepository;
import edu.baylor.ecs.hms.repository.RoleRepository;
import edu.baylor.ecs.hms.repository.UserRepository;
import edu.baylor.ecs.hms.service.HotelService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

/**
 * Populates initial data in the DB if needed
 */
@Component
public class DataPopulator implements ApplicationListener<ContextRefreshedEvent> {

    // rest template
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HotelService hotelService;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    // logger
    Logger logger = LoggerFactory.logger(DataPopulator.class);

    // shows if the DB setup is done
    private static boolean setupComplete = false;

    /**
     * Populates the DB if empty on Context Refreshed Event
     * @param event the refresh events
     */
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // see if already done
        if(setupComplete){
            return;
        }

        // setup the DB with any external dependencies
        // keep in mind this is transactional
        setup();
    }

    /**
     * Setups the DB
     */
    @Transactional
    public void setup() {

        logger.info("Beginning Setup ... ");

        // create andrew account
        if (!userRepository.existsByUsername("andrew")) {
            User andrew = new Customer();
            andrew.setUsername("andrew");
            andrew.setPassword(passwordEncoder.encode("password"));

            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set."));

            Set<Role> roles = new HashSet<>();
            roles.add(userRole);

            andrew.setRoles(roles);
            andrew.setFirstName("andrew");
            andrew.setLastName("andrew");
            andrew.setEmail("andrew@email.com");
            andrew.setPhoneNumber("1234567890");
            andrew.setAge(25L);

            userRepository.save(andrew);
        }

        // create andrew account
        if (!userRepository.existsByUsername("jalen")) {
            User andrew = new Customer();
            andrew.setUsername("jalen");
            andrew.setPassword(passwordEncoder.encode("password"));

            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set."));

            Set<Role> roles = new HashSet<>();
            roles.add(userRole);

            andrew.setRoles(roles);
            andrew.setFirstName("jalen");
            andrew.setLastName("jalen");
            andrew.setEmail("jalen@email.com");
            andrew.setPhoneNumber("1234567890");
            andrew.setAge(25L);

            userRepository.save(andrew);
        }

        hotelService.construct(new CreateScaffoldHotelRequest(
                "Hotel One",
                "123 Bear Place",
                "",
                "Waco",
                "Texas",
                "76798",
                4L,
                4L
        ));

        // create admin account if not exists
        if (!userRepository.existsByUsername(adminUsername)) {
            User admin = new HotelManager();
            admin.setUsername(adminUsername);
            admin.setPassword(passwordEncoder.encode(adminPassword));

            Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                    .orElseThrow(() -> new AppException("Admin Role not set."));
            Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new AppException("User Role not set."));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);

            admin.setRoles(roles);

            admin.setFirstName("admin");
            admin.setLastName("admin");
            admin.setEmail("admin@email.com");
            admin.setPhoneNumber("1234567890");
            admin.setAge(100L);

            userRepository.save(admin);
        }

        logger.info("End Setup");

        // set this so that this does not run again while the server is running
        setupComplete = true;
    }

    /**
     * Setups the DB
     */
    @Transactional
    public void refresh() {

        logger.info("Beginning Refresh ... ");

        setupComplete = false;

        setup();
    }
}