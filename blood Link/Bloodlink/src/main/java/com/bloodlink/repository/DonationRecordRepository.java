package com.bloodlink.repository;

import com.bloodlink.entity.DonationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRecordRepository extends JpaRepository<DonationRecord, Long> {
}
