package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodCollection;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodCollectionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodCollectionLogRepository extends JpaRepository<BloodCollectionLog,Long> {


}
