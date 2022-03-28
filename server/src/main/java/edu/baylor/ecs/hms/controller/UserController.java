/*
 * Filename: UserController
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.controller;

import edu.baylor.ecs.hms.exception.ResourceNotFoundException;
import edu.baylor.ecs.hms.model.auth.RoleName;
import edu.baylor.ecs.hms.model.auth.User;
import edu.baylor.ecs.hms.payload.UserIdentityAvailability;
import edu.baylor.ecs.hms.payload.UserProfile;
import edu.baylor.ecs.hms.payload.UserSummary;
import edu.baylor.ecs.hms.repository.UserRepository;
import edu.baylor.ecs.hms.security.CurrentUser;
import edu.baylor.ecs.hms.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controller for User data
 *
 * @author Andrew Walker
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns the current user
     * @param currentUser the current user's identifier
     * @return the current user
     */
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {

        Set<RoleName> roleNameSet = currentUser.getAuthorities().stream().map(authority -> Enum.valueOf(RoleName.class, authority.getAuthority())).collect(Collectors.toSet());
        // Gets first authority from UserPrincipal's list of authorities to use when creating userSummary
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), roleNameSet, currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), currentUser.getAge(), currentUser.getPhoneNumber());
    }

    /**
     * Checks a username's availability
     * @param username a username to check
     * @return a username's availability
     */
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    /**
     * Returns a user's data from a username
     * @param username a username
     * @return a user's data from a username
     */
    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return new UserProfile(user.getId(), user.getUsername(), user.getCreatedAt());
    }

}
