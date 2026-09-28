package com.bloodlink.repository;

import com.bloodlink.entity.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloodGroupRepository extends JpaRepository<BloodGroup, Long> {
    Optional<BloodGroup> findByGroupNameIgnoreCase(String groupName);
}