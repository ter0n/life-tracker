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
        List<WorkSkillEntity> workSkillEntities = workSkillService.getOnlyMainSkills();
        return workSkillEntities;
    }

}
