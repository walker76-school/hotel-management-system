/*
 * Filename: JwtAuthenticationResponse.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An access token JWT response
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
