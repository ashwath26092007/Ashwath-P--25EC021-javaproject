package com.bloodlink.controller;

import com.bloodlink.entity.BloodGroup;
import com.bloodlink.service.BloodGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-groups")
public class BloodGroupController {

    private final BloodGroupService bloodGroupService;

    public BloodGroupController(BloodGroupService bloodGroupService) {
        this.bloodGroupService = bloodGroupService;
    }

    @PostMapping
    public BloodGroup addBloodGroup(
            @RequestBody BloodGroup bloodGroup) {

        return bloodGroupService.addBloodGroup(bloodGroup);
    }

    @GetMapping
    public List<BloodGroup> getAllBloodGroups() {
        return bloodGroupService.getAllBloodGroups();
    }

    @GetMapping("/{id}")
    public BloodGroup getBloodGroupById(
            @PathVariable Long id) {

        return bloodGroupService.getBloodGroupById(id);
    }
}