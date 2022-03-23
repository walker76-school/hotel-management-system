/*
 * Filename: LoginRequest.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.payload;

import edu.baylor.ecs.hms.model.auth.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * A request to login
 *
 * @author Andrew Walker
 */
@Data
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private RoleName roleName;
}
