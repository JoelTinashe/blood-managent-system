package com.digitalkrapht.bloodbank.bloodbank.security.repository;

import com.digitalkrapht.bloodbank.bloodbank.security.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByName(String name);
    List<Permission> findByRoles_Id(long id);

    @Override
    void delete(Permission privilege);

}