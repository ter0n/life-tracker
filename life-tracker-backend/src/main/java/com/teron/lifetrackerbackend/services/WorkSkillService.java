package com.teron.lifetrackerbackend.services;

import com.teron.lifetrackerbackend.entities.WorkSkillEntity;
import com.teron.lifetrackerbackend.repositories.WorkSkillsCrudRepository;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class WorkSkillService {

    private final WorkSkillsCrudRepository workSkillsCrudRepository;
    private final UsersDataLinksService usersDataLinksService;

    public List<WorkSkillEntity> getAllWorkSkills() {
        return (List<WorkSkillEntity>) workSkillsCrudRepository.findAll();
    }

    public WorkSkillEntity getUserWorkSkills() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        UUID workSkillsId = usersDataLinksService.getWorkSkillsId(userName);
        Optional<WorkSkillEntity> optionalWorkSkill = workSkillsCrudRepository.findById(workSkillsId);
        return optionalWorkSkill.orElseThrow();
    }

    public List<WorkSkillEntity> getOnlyMainSkills() {
        return workSkillsCrudRepository.findAllByParentId(null);
    }

    public void createNewSkill(WorkSkillEntity entity) {
        workSkillsCrudRepository.save(entity);
    }

    /**
     * Создание корневой ноды пользователя
     * @param username имя пользователя
     * @return идентификатор созданной ноды
     */
    public UUID createUserMainSkill(String username) {
        WorkSkillEntity newSkill = new WorkSkillEntity();
        newSkill.setName(username + " main skill node");
        newSkill.setComment("Крневой элемент " + username);
        newSkill.setValue(10000);
        return workSkillsCrudRepository.save(newSkill).getId();
    }

    public void deleteSkill(WorkSkillEntity entity) {
        workSkillsCrudRepository.delete(entity);
    }
}
