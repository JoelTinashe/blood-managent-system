package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodCollection;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodCollectionRepository  extends JpaRepository<BloodCollection,Integer> {
    List<BloodCollection> findByEnabled(boolean status);
    Page<BloodCollection> findByEnabled(boolean status, Pageable pageable);
    Long countByEnabled(boolean status);

}
