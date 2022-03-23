/*
 * Filename: UserIdentityRequest.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The availability of a username
 *
 * @author Andrew Walker
 */
@Data
@AllArgsConstructor
public class UserIdentityAvailability {
    private Boolean available;
}
