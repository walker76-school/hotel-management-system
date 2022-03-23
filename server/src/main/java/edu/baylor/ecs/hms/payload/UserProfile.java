/*
 * Filename: UserProfile.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

/**
 * A user's profile data
 *
 * Mostly unused but could be extended in future for more data
 */
@Data
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String username;
    private Instant joinedAt;
}
