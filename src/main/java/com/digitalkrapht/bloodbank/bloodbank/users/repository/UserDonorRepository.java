package com.digitalkrapht.bloodbank.bloodbank.users.repository;

import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserDonorRepository extends JpaRepository <UserDonor ,String>{
    List<UserDonor> findByEnabled(boolean status);
    Page<UserDonor> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);
    Page<UserDonor> findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String  firstName,String  lastname, Pageable pageable);
    Page<UserDonor> findDistinctByFirstNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualOrLastNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String  firstName, LocalDateTime dayStart, LocalDateTime dayEnd, String  lastname, LocalDateTime dayStart2, LocalDateTime dayEnd2, Pageable pageable);
    Page<UserDonor> findDistinctByFirstNameContainingIgnoreCaseAndEnabledOrLastNameContainingIgnoreCaseAndEnabled(String  firstName, boolean status,String  lastname, boolean status2,Pageable pageable);
}
