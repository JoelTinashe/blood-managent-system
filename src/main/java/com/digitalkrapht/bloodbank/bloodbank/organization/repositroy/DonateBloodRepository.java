package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodCollectionLog;
import com.digitalkrapht.bloodbank.bloodbank.users.models.DonateBlood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonateBloodRepository extends JpaRepository<DonateBlood,Long> {



}
