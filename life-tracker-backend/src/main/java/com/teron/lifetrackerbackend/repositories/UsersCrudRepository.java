package com.teron.lifetrackerbackend.repositories;

import com.teron.lifetrackerbackend.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersCrudRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}
