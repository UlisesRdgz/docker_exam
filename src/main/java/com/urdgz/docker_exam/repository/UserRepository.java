package com.urdgz.docker_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.urdgz.docker_exam.model.User;

/**
 * UserRepository is an interface for CRUD operations on a repository for the User type.
 * It extends JpaRepository to provide JPA functionalities for the User entity.
 * @author Ulises Rodríguez García.
 */
public interface UserRepository extends JpaRepository<User, Integer> { 
}
