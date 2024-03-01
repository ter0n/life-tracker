package com.teron.lifetrackerbackend.repositories;

import com.teron.lifetrackerbackend.entities.WorkSkillEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface WorkSkillsCrudRepository extends CrudRepository<WorkSkillEntity, UUID> {

//    @Query(value = "SELECT ws FROM WorkSkill ws")
//    List<WorkSkill> getAllData();

    List<WorkSkillEntity> findAllByParentId(UUID parentId);

}
