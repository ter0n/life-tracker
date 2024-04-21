package com.teron.lifetrackerbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users_data_links")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDataLinksEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private UUID workSkillsId;

}
