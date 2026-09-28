
package com.bloodlink.controller;

import com.bloodlink.entity.Donor;
import com.bloodlink.service.DonorService;
import com.bloodlink.repository.DonorRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;
    private final DonorRepository donorRepository;

    public DonorController(
            DonorService donorService,
            DonorRepository donorRepository) {

        this.donorService = donorService;
        this.donorRepository = donorRepository;
    }

    @PostMapping
    public Donor registerDonor(@RequestBody Donor donor) {
        return donorService.registerDonor(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/blood-group/{groupName}")
    public List<Donor> searchByBloodGroup(
            @PathVariable String groupName) {

        return donorService.searchByBloodGroup(groupName);
    }

    @GetMapping("/city/{city}")
    public List<Donor> searchByCity(
            @PathVariable String city) {

        return donorService.searchByCity(city);
    }

    @GetMapping("/search")
    public List<Donor> searchByBloodGroupAndCity(
            @RequestParam String groupName,
            @RequestParam String city) {

        return donorService.searchByBloodGroupAndCity(
                groupName, city);
    }

    @GetMapping("/{id}/eligibility")
    public boolean checkEligibility(@PathVariable Long id) {

        Donor donor = donorService.getDonorById(id);

        return donorService.isEligibleToDonate(donor);
    }

    @GetMapping("/count-by-blood-group")
    public List<Map<String, Object>> countDonorsByBloodGroup() {

        List<Object[]> results =
                donorRepository.countDonorsByBloodGroup();

        List<Map<String, Object>> response = new ArrayList<>();

        for (Object[] row : results) {

            Map<String, Object> data = new LinkedHashMap<>();

            data.put("bloodGroup", row[0]);
            data.put("totalDonors", row[1]);

            response.add(data);
        }

        return response;
    }
}
