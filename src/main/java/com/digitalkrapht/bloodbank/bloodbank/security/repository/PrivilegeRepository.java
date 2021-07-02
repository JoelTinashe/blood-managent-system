package com.digitalkrapht.bloodbank.bloodbank.security.repository;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);



}
