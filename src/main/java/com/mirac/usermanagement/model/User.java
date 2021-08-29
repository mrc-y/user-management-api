package com.mirac.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Pojo class which defines the user model.
 * Validation annotations are used to make sure the data integrity.
 *
 * @author miracy
 */
@Data
public class User {

    private String id;

    @NotBlank(message = "Username can not be blank!")
    @Size(min = 2, max = 30, message = "Username size must be between 2-30 characters.")
    private String username;

    @NotBlank(message = "Password can not be blank!")
    @Size(min = 8, max = 30, message = "Password size must be between 8-30 characters.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email
    private String email;

    private boolean loggedIn;
}
