package com.urdgz.docker_exam.service;

import java.util.List;

import com.urdgz.docker_exam.dto.UserDto;
import com.urdgz.docker_exam.model.User;

/**
 * UserService is an interface that defines the contract for user-related operations.
 * It includes methods for retrieving all users, creating a user, updating a user partially, and deleting a user.
 * @author Ulises Rodríguez García.
 */
public interface UserService {

    /**
     * Retrieves a list of all users.
     * @return a list of User objects
     */
    List<User> getAllUsers();

    /**
     * Creates a new user.
     * @param userDto the UserDto object to be created
     * @return the created User object
     */
    User createUser(User user);

    /**
     * Updates an existing user partially with the given UserDto.
     * @param id      the ID of the user to be updated
     * @param userDto the UserDto object containing the updated user information
     * @return the updated User object
     */
    User patchUser(Integer id, UserDto userDto);

    /**
     * Deletes a user by their ID.
     * @param id the ID of the user to be deleted
     */
    void deleteUser(Integer id);
}
