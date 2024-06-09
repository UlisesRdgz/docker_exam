package com.urdgz.docker_exam.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.urdgz.docker_exam.dto.UserDto;
import com.urdgz.docker_exam.exception.CustomApiException;
import com.urdgz.docker_exam.model.User;
import com.urdgz.docker_exam.repository.UserRepository;

import jakarta.transaction.Transactional;

/**
 * UserServiceImp is an implementation of the UserService interface.
 * It provides the actual logic for the operations defined in the UserService interface,
 * such as retrieving all users, creating a user, updating a user partially, and deleting a user.
 * @author Ulises Rodríguez García.
 */
@Service
public class UserServiceImp implements UserService {

    /**
     * The UserRepository instance used to perform CRUD operations on User entities.
     */
    private final UserRepository userRepository;

    /**
     * Constructs a new UserServiceImp with the specified UserRepository.
     * @param userRepository the UserRepository to be used for database operations
     */
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all users.
     * @return a list of User objects
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Creates a new user.
     * @param user the User object to be created
     * @return the created User object
     */
    @Override
    @Transactional
    public User createUser(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) 
            throw new CustomApiException(HttpStatus.CONFLICT, "A user with the same id already exists");

        if (userRepository.existsByEmail(user.getEmail())) 
            throw new CustomApiException(HttpStatus.CONFLICT, "A user with the same email already exists");

        return userRepository.save(user);
    }

    /**
     * Updates an existing user partially with the given UserDto.
     * @param id      the ID of the user to be updated
     * @param userDto the UserDto object containing the updated user information
     * @return the updated User object
     */
    @Override
    @Transactional
    public User patchUser(Integer id, UserDto userDto) {
        return userRepository.findById(id).map(existingUser -> {
            if (userDto.getName() != null && !existingUser.getName().equals(userDto.getName())) 
                existingUser.setName(userDto.getName());
            
            if (userDto.getEmail() != null && !existingUser.getEmail().equals(userDto.getEmail())) {
                if (userRepository.existsByEmail(userDto.getEmail())) 
                    throw new CustomApiException(HttpStatus.CONFLICT, "A user with the same email already exists");
                
                existingUser.setEmail(userDto.getEmail());
            }
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new CustomApiException(HttpStatus.NOT_FOUND, "User not found with id " + id));
    }

    /**
     * Deletes a user by their ID.
     * @param id the ID of the user to be deleted
     */
    @Override
    public void deleteUser(Integer id) {
        if (userRepository.existsById(id)) 
            userRepository.deleteById(id);

        else 
            throw new CustomApiException(HttpStatus.NOT_FOUND, "User not found with id " + id);
    }
}
