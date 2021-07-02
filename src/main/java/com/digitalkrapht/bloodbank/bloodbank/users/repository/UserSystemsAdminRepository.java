package com.digitalkrapht.bloodbank.bloodbank.users.repository;

import com.digitalkrapht.bloodbank.bloodbank.users.models.UserSystemAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserSystemsAdminRepository extends JpaRepository<UserSystemAdmin,String> {

    List<UserSystemAdmin> findByEnabled(boolean status);
    Page<UserSystemAdmin> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);
    Page<UserSystemAdmin> findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String  firstName,String  lastname, Pageable pageable);
    Page<UserSystemAdmin> findDistinctByFirstNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualOrLastNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String  firstName, LocalDateTime dayStart, LocalDateTime dayEnd, String  lastname, LocalDateTime dayStart2, LocalDateTime dayEnd2, Pageable pageable);
    Page<UserSystemAdmin> findDistinctByFirstNameContainingIgnoreCaseAndEnabledOrLastNameContainingIgnoreCaseAndEnabled(String  firstName, boolean status,String  lastname, boolean status2,Pageable pageable);
}
