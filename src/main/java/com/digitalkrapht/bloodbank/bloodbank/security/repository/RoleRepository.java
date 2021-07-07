package com.digitalkrapht.bloodbank.bloodbank.security.repository;

import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.security.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


     Optional<Role> findByName(RoleName name) ;

    Boolean existsByName(RoleName roleName);

    List<Role> findBySelfRegEnabled(boolean b);
}
