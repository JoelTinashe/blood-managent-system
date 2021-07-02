package com.digitalkrapht.bloodbank.bloodbank.users.repository;

import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserBackOfficeAdminRepository extends JpaRepository<UserBackOfficeAdmin,String> {

    List<UserBackOfficeAdmin> findByEnabled(boolean status);
    Page<UserBackOfficeAdmin> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);
    Page<UserBackOfficeAdmin> findDistinctByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String  firstName, String  lastname, Pageable pageable);
    Page<UserBackOfficeAdmin> findDistinctByFirstNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualOrLastNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String  firstName, LocalDateTime dayStart, LocalDateTime dayEnd, String  lastname, LocalDateTime dayStart2, LocalDateTime dayEnd2, Pageable pageable);
    Page<UserBackOfficeAdmin> findDistinctByFirstNameContainingIgnoreCaseAndEnabledOrLastNameContainingIgnoreCaseAndEnabled(String  firstName, boolean status, String  lastname, boolean status2, Pageable pageable);

}
