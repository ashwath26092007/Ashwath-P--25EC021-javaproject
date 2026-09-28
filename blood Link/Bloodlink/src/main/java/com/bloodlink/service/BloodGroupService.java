package com.bloodlink.service;

import com.bloodlink.entity.BloodGroup;
import com.bloodlink.repository.BloodGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodGroupService {

    private final BloodGroupRepository bloodGroupRepository;

    public BloodGroupService(BloodGroupRepository bloodGroupRepository) {
        this.bloodGroupRepository = bloodGroupRepository;
    }

    public BloodGroup addBloodGroup(BloodGroup bloodGroup) {
        return bloodGroupRepository.save(bloodGroup);
    }

    public List<BloodGroup> getAllBloodGroups() {
        return bloodGroupRepository.findAll();
    }

    public BloodGroup getBloodGroupById(Long id) {
        return bloodGroupRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Blood group not found with id: " + id));
    }
}