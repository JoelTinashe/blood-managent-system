package com.digitalkrapht.bloodbank.bloodbank.users.repository;

import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserBackOfficeAgentRepository extends JpaRepository<UserBackOfficeAgent,String> {

    List<UserBackOfficeAgent> findByEnabled(boolean status);
    Page<UserBackOfficeAgent> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);
    Page<UserBackOfficeAgent> findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String  firstName,String  lastname, Pageable pageable);
    Page<UserBackOfficeAgent> findDistinctByFirstNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualOrLastNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String  firstName, LocalDateTime dayStart, LocalDateTime dayEnd, String  lastname, LocalDateTime dayStart2, LocalDateTime dayEnd2, Pageable pageable);
    Page<UserBackOfficeAgent> findDistinctByFirstNameContainingIgnoreCaseAndEnabledOrLastNameContainingIgnoreCaseAndEnabled(String  firstName, boolean status,String  lastname, boolean status2,Pageable pageable);
}
    

