package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.Organisation;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {


    List<Organisation> findByEnabled(boolean status);
    Page<Organisation> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);

}


