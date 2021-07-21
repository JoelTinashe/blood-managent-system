package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;


import com.digitalkrapht.bloodbank.bloodbank.users.models.DonorLogs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonorLogRepository extends JpaRepository<DonorLogs,Long> {

}
