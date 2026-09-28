package com.bloodlink.controller;

import com.bloodlink.entity.DonationRecord;
import com.bloodlink.service.DonationRecordService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationRecordController {

    private final DonationRecordService donationRecordService;

    public DonationRecordController(
            DonationRecordService donationRecordService) {

        this.donationRecordService = donationRecordService;
    }

    @PostMapping("/donor/{donorId}")
    public DonationRecord recordDonation(
            @PathVariable Long donorId,
            @RequestParam LocalDate donationDate) {

        return donationRecordService.recordDonation(
                donorId, donationDate);
    }

    @GetMapping
    public List<DonationRecord> getAllDonationRecords() {
        return donationRecordService.getAllDonationRecords();
    }
}