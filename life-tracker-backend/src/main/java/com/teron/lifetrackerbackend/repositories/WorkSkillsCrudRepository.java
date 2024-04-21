package com.teron.lifetrackerbackend.repositories;

import com.teron.lifetrackerbackend.entities.WorkSkillEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkSkillsCrudRepository extends CrudRepository<WorkSkillEntity, UUID> {

//    @Query(value = "SELECT ws FROM WorkSkill ws")
//    List<WorkSkill> getAllData();

    List<WorkSkillEntity> findAllByParentId(UUID parentId);

    @NonNull
    Optional<WorkSkillEntity> findById(@NonNull UUID id);

}
