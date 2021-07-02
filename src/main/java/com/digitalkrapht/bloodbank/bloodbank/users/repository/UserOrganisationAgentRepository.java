package com.digitalkrapht.bloodbank.bloodbank.users.repository;

import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserOrganisationAgentRepository extends JpaRepository<UserOrganizationAgent,String> {

    List<UserOrganizationAgent> findByEnabled(boolean status);

    Page<UserOrganizationAgent> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);
    Page<UserOrganizationAgent> findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String  firstName,String  lastname, Pageable pageable);
    Page<UserOrganizationAgent> findDistinctByFirstNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualOrLastNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String  firstName, LocalDateTime dayStart, LocalDateTime dayEnd, String  lastname, LocalDateTime dayStart2, LocalDateTime dayEnd2, Pageable pageable);
    Page<UserOrganizationAgent> findDistinctByFirstNameContainingIgnoreCaseAndEnabledOrLastNameContainingIgnoreCaseAndEnabled(String  firstName, boolean status,String  lastname, boolean status2,Pageable pageable);


}

