package com.urdgz.docker_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urdgz.docker_exam.model.User;

/**
 * UserRepository is an interface for CRUD operations on a repository for the User type.
 * It extends JpaRepository to provide JPA functionalities for the User entity.
 * @author Ulises Rodríguez García.
 */
public interface UserRepository extends JpaRepository<User, Integer> { 

    /**
     * Checks if a User with the given email exists in the database.
     * @param email the email to check for existence
     * @return true if an entity with the given email exists, false otherwise
     */
    boolean existsByEmail(String email);
}
