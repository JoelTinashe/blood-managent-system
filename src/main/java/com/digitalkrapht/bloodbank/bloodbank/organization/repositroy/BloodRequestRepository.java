package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import com.digitalkrapht.bloodbank.bloodbank.users.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories

public interface BloodRequestRepository extends JpaRepository<BloodRequest,Long> {
    List<BloodRequest> findByEnabled(boolean status);
    Page<BloodRequest> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);
    Optional<BloodRequest>findByBloodStatus( BloodStatus bloodStatus);


}
