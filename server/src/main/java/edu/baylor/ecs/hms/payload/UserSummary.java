/*
 * Filename: UserSummary.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.payload;

import edu.baylor.ecs.hms.model.auth.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * A summary of technical aspects of a user's data
 *
 * @author Andrew Walker
 */
@Data
@AllArgsConstructor
public class UserSummary {
    private Long id;
    private String username;
    private Set<RoleName> roleName;
}
