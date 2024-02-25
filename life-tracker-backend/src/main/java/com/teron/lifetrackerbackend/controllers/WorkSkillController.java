package com.teron.lifetrackerbackend.controllers;

import com.teron.lifetrackerbackend.models.WorkSkill;
import com.teron.lifetrackerbackend.services.WorkSkillService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@CrossOrigin
@RequestMapping(value = "/work-skills")
public class WorkSkillController {

    private final WorkSkillService workSkillService;

    @GetMapping(value = "/get-all")
    @ResponseStatus(value = HttpStatus.OK)
    public List<WorkSkill> getAllData() {
        List<WorkSkill> workSkills = workSkillService.getAllWorkSkills();
        return workSkills;
    }

}
