package com.teron.lifetrackerbackend.services;

import com.teron.lifetrackerbackend.entities.WorkSkillEntity;
import com.teron.lifetrackerbackend.repositories.WorkSkillsCrudRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class WorkSkillService {

    private final WorkSkillsCrudRepository workSkillsCrudRepository;

    public List<WorkSkillEntity> getAllWorkSkills() {
        return (List<WorkSkillEntity>) workSkillsCrudRepository.findAll();
    }

    public List<WorkSkillEntity> getOnlyMainSkills() {
        return workSkillsCrudRepository.findAllByParentId(null);
    }

    public void createNewSkill(WorkSkillEntity entity) {
        workSkillsCrudRepository.save(entity);
    }
}
