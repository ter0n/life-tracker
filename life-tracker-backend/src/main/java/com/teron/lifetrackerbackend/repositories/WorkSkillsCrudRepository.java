package com.teron.lifetrackerbackend.repositories;

import com.teron.lifetrackerbackend.models.WorkSkill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface WorkSkillsCrudRepository extends CrudRepository<WorkSkill, UUID> {

//    @Query(value = "SELECT ws FROM WorkSkill ws")
//    List<WorkSkill> getAllData();

}
