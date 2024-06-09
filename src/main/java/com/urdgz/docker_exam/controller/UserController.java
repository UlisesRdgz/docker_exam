package com.urdgz.docker_exam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urdgz.docker_exam.dto.SuccessResponse;
import com.urdgz.docker_exam.dto.UserDto;
import com.urdgz.docker_exam.model.User;
import com.urdgz.docker_exam.service.UserService;

import jakarta.validation.Valid;

/**
 * UserController is a REST controller that handles HTTP requests for user-related operations.
 * It includes endpoints for retrieving all users, creating a user, updating a user partially, and deleting a user.
 * @author Ulises Rodríguez García.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    
    /**
     * The UserService instance used to perform user-related operations.
     */
    private final UserService userService;

    /**
     * Constructs a new UserController with the specified UserService.
     * @param userService the UserService to be used for user-related operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves a list of all users.
     * @return a ResponseEntity containing a SuccessResponse with the list of users and the HTTP status
     */
    @GetMapping
    public ResponseEntity<SuccessResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        SuccessResponse<List<User>> response = new SuccessResponse<>(
            HttpStatus.OK,
            "Users retrieved successfully",
            users
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Creates a new user.
     * @param user the User object to be created
     * @return a ResponseEntity containing a SuccessResponse with the created user and the HTTP status
     */
    @PostMapping
    public ResponseEntity<SuccessResponse<User>> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        SuccessResponse<User> response = new SuccessResponse<>(
            HttpStatus.CREATED,
            "User created successfully",
            createdUser
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Updates an existing user partially with the given UserDto.
     * @param id      the ID of the user to be updated
     * @param userDto the UserDto object containing the updated user information
     * @return a ResponseEntity containing a SuccessResponse with the updated user and the HTTP status
     */
    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<User>> patchUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
        User patchedUser = userService.patchUser(id, userDto);
        SuccessResponse<User> response = new SuccessResponse<>(
            HttpStatus.OK,
            "User patched successfully",
            patchedUser
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes a user by their ID.
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity containing a SuccessResponse with the HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        SuccessResponse<Void> response = new SuccessResponse<>(
            HttpStatus.OK,
            "User deleted successfully",
            null
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

