package com.bloodlink.repository;

import com.bloodlink.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByCityIgnoreCase(String city);

    List<Donor> findByBloodGroupGroupNameIgnoreCase(String groupName);

    List<Donor> findByBloodGroupGroupNameIgnoreCaseAndCityIgnoreCase(
            String groupName,
            String city
    );

    @Query("SELECT d.bloodGroup.groupName, COUNT(d) " +
            "FROM Donor d " +
            "GROUP BY d.bloodGroup.groupName")
    List<Object[]> countDonorsByBloodGroup();
}