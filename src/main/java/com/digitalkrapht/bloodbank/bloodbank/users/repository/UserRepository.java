package com.digitalkrapht.bloodbank.bloodbank.users.repository;

import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.users.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User,String>{

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByTokenHash(String tokenHash);

    Boolean existsByUsername(String username);

    Boolean existsByTokenHash(String tokenHash);


    Boolean existsByEmail(String email);

    Page<User> findByRolesIn(List<Role> roles, Pageable pageable);

    List<User> findByRolesIn(List<Role> roles);

    List<User> findByRolesInAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(List<Role> roles, LocalDateTime dayStart, LocalDateTime dayEnd);

    Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String query, String query2, Pageable pageable);

    Page<User> findByFirstNameContainingIgnoreCaseAndEnabledOrLastNameContainingIgnoreCaseAndEnabled(String query, boolean status, String query2, boolean status2, Pageable pageable);

    List<User> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(LocalDateTime dayStart, LocalDateTime dayEnd);

    Page<User> findByFirstNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqualOrLastNameContainingIgnoreCaseAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(String query, LocalDateTime dayStart, LocalDateTime dayEnd, String query2, LocalDateTime dayStart2, LocalDateTime dayEnd2, Pageable pageable);

}
