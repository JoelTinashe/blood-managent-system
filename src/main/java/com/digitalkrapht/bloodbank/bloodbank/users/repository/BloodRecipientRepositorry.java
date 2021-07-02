package com.digitalkrapht.bloodbank.bloodbank.users.repository;

import com.digitalkrapht.bloodbank.bloodbank.users.models.BloodRecipient;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BloodRecipientRepositorry extends JpaRepository<BloodRecipient,String> {

    List<BloodRecipient> findByEnabled(boolean status);
    Page<BloodRecipient> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);
    Page<BloodRecipient> findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String  firstName,String  lastname, Pageable pageable);
    Page<BloodRecipient> findDistinctByFirstNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualOrLastNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String  firstName, LocalDateTime dayStart, LocalDateTime dayEnd, String  lastname, LocalDateTime dayStart2, LocalDateTime dayEnd2, Pageable pageable);
    Page<BloodRecipient> findDistinctByFirstNameContainingIgnoreCaseAndEnabledOrLastNameContainingIgnoreCaseAndEnabled(String  firstName, boolean status,String  lastname, boolean status2,Pageable pageable);
}
    

