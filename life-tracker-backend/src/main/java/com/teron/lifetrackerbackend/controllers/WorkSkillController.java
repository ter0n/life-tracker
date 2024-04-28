package com.teron.lifetrackerbackend.controllers;

import com.teron.lifetrackerbackend.entities.WorkSkillEntity;
import com.teron.lifetrackerbackend.services.WorkSkillService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping(value = "/work-skills")
public class WorkSkillController {

    private final WorkSkillService workSkillService;

    @GetMapping(value = "/get-all")
    @ResponseStatus(value = HttpStatus.OK)
    public List<WorkSkillEntity> getAllData() {
        List<WorkSkillEntity> workSkillEntities = workSkillService.getAllWorkSkills();
        return workSkillEntities;
    }

//    @GetMapping(value = "/get-user-work-skills")
//    public WorkSkillEntity getUserWorkSkills() {
//        return workSkillService.getUserWorkSkills();
//    }

    @GetMapping(value = "/get-user-work-skills")
    public List<WorkSkillEntity> getUserWorkSkills() {
        return workSkillService.getUserWorkSkillsList();
    }

    @PostMapping("/create-skill")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkSkillEntity> createNewWorkSkill(@RequestBody WorkSkillEntity entity) {
        workSkillService.createNewSkill(entity);
        return workSkillService.getAllWorkSkills();
    }

    @PostMapping("/edit-skill")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkSkillEntity> editSkill(@RequestBody WorkSkillEntity entity) {
        WorkSkillEntity edited = workSkillService.editSkill(entity);
        if (edited == null) return null;
        return workSkillService.getUserWorkSkillsList();
    }

    @DeleteMapping("/delete-skill")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkSkillEntity> deleteWorkSkill(@RequestBody WorkSkillEntity entity) {
        workSkillService.deleteSkill(entity);
        return workSkillService.getUserWorkSkillsList();
    }

}
