/*
 * Filename: DataPopulator.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.startup;

import edu.baylor.ecs.hms.exception.AppException;
import edu.baylor.ecs.hms.model.auth.Role;
import edu.baylor.ecs.hms.model.auth.RoleName;
import edu.baylor.ecs.hms.model.auth.User;
import edu.baylor.ecs.hms.model.people.HotelManager;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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