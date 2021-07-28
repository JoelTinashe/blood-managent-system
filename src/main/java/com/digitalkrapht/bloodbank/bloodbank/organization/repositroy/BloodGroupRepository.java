package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.Organisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BloodGroupRepository extends JpaRepository<BloodGroup, Integer> {

    List<BloodGroup> findByEnabled(boolean status);
    Page<BloodGroup> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);

}


