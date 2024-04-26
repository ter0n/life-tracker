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
        System.out.println("Test! " + entity);
        workSkillService.createNewSkill(entity);
        return workSkillService.getAllWorkSkills();
    }

    @DeleteMapping("/delete-skill")
    @ResponseStatus(HttpStatus.OK)
    public List<WorkSkillEntity> deleteWorkSkill(@RequestBody WorkSkillEntity entity) {
        System.out.println("Delete test! " + entity);
        workSkillService.deleteSkill(entity);
        return workSkillService.getAllWorkSkills();
    }

}
