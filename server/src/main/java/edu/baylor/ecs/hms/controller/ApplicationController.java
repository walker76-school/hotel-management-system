/*
 * Filename: AdminController.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Application status
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api/app")
public class ApplicationController {

    /**
     * Heartbeat
     */
    @GetMapping("/heartbeat")
    public String authenticateUser() {
        return "[HMS] Alive";
    }
}
