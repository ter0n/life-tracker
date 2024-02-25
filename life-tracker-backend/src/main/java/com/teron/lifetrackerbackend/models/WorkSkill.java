package com.teron.lifetrackerbackend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "work_skills")
@Data
public class WorkSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private UUID parentId;

    private String comment;

    private String name;

}
