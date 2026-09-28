package com.bloodlink.service;

import com.bloodlink.entity.BloodGroup;
import com.bloodlink.entity.Donor;
import com.bloodlink.repository.BloodGroupRepository;
import com.bloodlink.repository.DonorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DonorService {

    private final DonorRepository donorRepository;
    private final BloodGroupRepository bloodGroupRepository;

    public DonorService(DonorRepository donorRepository, BloodGroupRepository bloodGroupRepository) {
        this.donorRepository = donorRepository;
        this.bloodGroupRepository = bloodGroupRepository;
    }

    public Donor registerDonor(Donor donor) {
        if (donor.getBloodGroup() != null) {
            String groupName = donor.getBloodGroup().getGroupName();
            if (groupName != null && !groupName.trim().isEmpty()) {
                String normalized = groupName.trim().toUpperCase();
                BloodGroup bg = bloodGroupRepository.findByGroupNameIgnoreCase(normalized)
                        .orElseGet(() -> bloodGroupRepository.save(new BloodGroup(normalized)));
                donor.setBloodGroup(bg);
            } else if (donor.getBloodGroup().getId() != null) {
                BloodGroup bg = bloodGroupRepository.findById(donor.getBloodGroup().getId())
                        .orElseThrow(() -> new RuntimeException("Blood group not found with id: " + donor.getBloodGroup().getId()));
                donor.setBloodGroup(bg);
            }
        }
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public List<Donor> searchByBloodGroup(String groupName) {
        return donorRepository
                .findByBloodGroupGroupNameIgnoreCase(groupName)
                .stream()
                .filter(this::isEligibleToDonate)
                .toList();
    }

    public List<Donor> searchByCity(String city) {
        return donorRepository
                .findByCityIgnoreCase(city)
                .stream()
                .filter(this::isEligibleToDonate)
                .toList();
    }

    public List<Donor> searchByBloodGroupAndCity(
            String groupName,
            String city) {

        return donorRepository
                .findByBloodGroupGroupNameIgnoreCaseAndCityIgnoreCase(
                        groupName, city)
                .stream()
                .filter(this::isEligibleToDonate)
                .toList();
    }

    public boolean isEligibleToDonate(Donor donor) {

        if (donor.getLastDonationDate() == null) {
            return true;
        }

        long daysSinceLastDonation =
                ChronoUnit.DAYS.between(
                        donor.getLastDonationDate(),
                        LocalDate.now());

        return daysSinceLastDonation >= 90;
    }

    public Donor getDonorById(Long id) {
        return donorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Donor not found with id: " + id));
    }

    public List<Object[]> getDonorCountByBloodGroup() {
        return donorRepository.countDonorsByBloodGroup();
    }
}