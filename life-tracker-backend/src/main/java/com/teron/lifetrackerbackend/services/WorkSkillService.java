package com.teron.lifetrackerbackend.services;

import com.teron.lifetrackerbackend.models.WorkSkill;
import com.teron.lifetrackerbackend.repositories.WorkSkillsCrudRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class WorkSkillService {

    private final WorkSkillsCrudRepository workSkillsCrudRepository;

    public List<WorkSkill> getAllWorkSkills() {
        return (List<WorkSkill>) workSkillsCrudRepository.findAll();
    }
}
