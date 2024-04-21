package com.teron.lifetrackerbackend.repositories;

import com.teron.lifetrackerbackend.entities.UsersDataLinksEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsersDataLinksCrudRepository extends CrudRepository<UsersDataLinksEntity, UUID> {

    UsersDataLinksEntity findByUsername(String username);

}
