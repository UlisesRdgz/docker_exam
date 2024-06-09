package com.urdgz.docker_exam.dto;

import jakarta.validation.constraints.Email;

/**
 * UserDto is a Data Transfer Object for transferring user data.
 * It contains the user's name and email.
 * @author Ulises Rodríguez García.
 */
public class UserDto {
    
    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email of the user.
     */
    @Email(message = "Email should be valid")
    private String email;

    /**
     * Gets the name of the user.
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email of the user.
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}