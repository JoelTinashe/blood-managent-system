package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepository extends JpaRepository<BloodRequest,Long> {
    List<BloodRequest> findByEnabled(boolean status);
    Page<BloodRequest> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);

}
