package com.urdgz.docker_exam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents a user entity with an ID, name, and email.
 * Mapped to the "Users" table in the database.
 * @author Ulises Rodríguez García.
 */
@Entity
@Table(name = "Users")
public class User {

    /**
     * The unique identifier for the entity.
     * This field is automatically generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name of the entity.
     * This field is mandatory and cannot be null.
     */
    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    /**
     * The email of the entity.
     * This field is mandatory and cannot be null.
     */
    @Column(nullable = false)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    /**
     * Gets the ID of the user.
     * @return the ID of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     * @param id the new ID of the user
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name the new name of the user
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
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
