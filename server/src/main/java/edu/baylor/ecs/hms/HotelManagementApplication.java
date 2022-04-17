/*
 * Filename: HotelManagementApplication.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms;

import edu.baylor.ecs.hms.exception.AppException;
import edu.baylor.ecs.hms.model.auth.Role;
import edu.baylor.ecs.hms.model.auth.RoleName;
import edu.baylor.ecs.hms.model.auth.User;
import edu.baylor.ecs.hms.model.people.HotelManager;
import edu.baylor.ecs.hms.repository.RoleRepository;
import edu.baylor.ecs.hms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

/**
 * Runner application for HotelManagementApplication
 */
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = { "edu.baylor.ecs.hms.*" })
public class HotelManagementApplication {



    /**
     * Sets timezone of application
     */
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

    /**
     * Runs the application
     * @param args command-line args
     */
	public static void main(String[] args) {
		SpringApplication.run(HotelManagementApplication.class, args);
	}

    /**
     * Returns the RestTemplate for injection
     * @return the RestTemplate for injection
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Adds an admin account to the database
     * @return command line runner for adding admin account
     */
    @Bean
    public CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {

            @Autowired
            private UserRepository userRepository;

            @Autowired
            private RoleRepository roleRepository;

            @Autowired
            PasswordEncoder passwordEncoder;

            @Override
            public void run(String... args) {



            }
        };
    }
}
