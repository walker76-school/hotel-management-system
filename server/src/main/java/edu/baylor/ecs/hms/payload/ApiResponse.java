/*
 * Filename: ApiResponse.java
 * Author: Andrew Walker
 */

package edu.baylor.ecs.hms.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a response from the API
 */
@AllArgsConstructor
@Data
public class ApiResponse {
    private Boolean success;
    private String message;
}
