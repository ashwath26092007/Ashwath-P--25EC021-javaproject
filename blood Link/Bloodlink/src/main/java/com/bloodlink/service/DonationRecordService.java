package com.bloodlink.service;

import com.bloodlink.entity.DonationRecord;
import com.bloodlink.entity.Donor;
import com.bloodlink.repository.DonationRecordRepository;
import com.bloodlink.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonationRecordService {

    private final DonationRecordRepository donationRecordRepository;
    private final DonorRepository donorRepository;

    public DonationRecordService(
            DonationRecordRepository donationRecordRepository,
            DonorRepository donorRepository) {

        this.donationRecordRepository = donationRecordRepository;
        this.donorRepository = donorRepository;
    }

    public DonationRecord recordDonation(Long donorId, LocalDate donationDate) {

        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() ->
                        new RuntimeException("Donor not found with id: " + donorId));

        DonationRecord record = new DonationRecord();
        record.setDonor(donor);
        record.setDonationDate(donationDate);

        donor.setLastDonationDate(donationDate);
        donorRepository.save(donor);

        return donationRecordRepository.save(record);
    }

    public List<DonationRecord> getAllDonationRecords() {
        return donationRecordRepository.findAll();
    }
}