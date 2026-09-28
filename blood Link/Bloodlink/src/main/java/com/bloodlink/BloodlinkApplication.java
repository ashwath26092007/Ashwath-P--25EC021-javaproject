package com.bloodlink;

import com.bloodlink.entity.BloodGroup;
import com.bloodlink.repository.BloodGroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BloodlinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BloodlinkApplication.class, args);
    }

    @Bean
    public CommandLineRunner initBloodGroups(BloodGroupRepository bloodGroupRepository) {
        return args -> {
            List<String> groups = List.of("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-");
            for (String group : groups) {
                if (bloodGroupRepository.findByGroupNameIgnoreCase(group).isEmpty()) {
                    bloodGroupRepository.save(new BloodGroup(group));
                }
            }
        };
    }
}
