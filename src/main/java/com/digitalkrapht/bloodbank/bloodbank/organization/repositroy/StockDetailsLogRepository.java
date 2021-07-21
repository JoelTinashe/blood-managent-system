package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.StockDetails;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.StockDetailsLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDetailsLogRepository extends JpaRepository<StockDetailsLog,Long> {

//    List<StockDetails> findByEnabled(boolean status);
//    Page<StockDetails> findByEnabled(boolean status, Pageable pageable);
//    Long countByEnabled(boolean status);


}
