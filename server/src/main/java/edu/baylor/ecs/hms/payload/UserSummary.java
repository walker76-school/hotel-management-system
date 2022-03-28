/*
 * Filename: UserSummary.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.payload;

import edu.baylor.ecs.hms.model.auth.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private String firstName;
    private String lastName;
    private String email;
    private Long age;
    private String phoneNumber;
}
