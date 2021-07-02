package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDetailsLogRepository extends JpaRepository<StockDetails,Integer> {

//    List<StockDetails> findByEnabled(boolean status);
//    Page<StockDetails> findByEnabled(boolean status, Pageable pageable);
//    Long countByEnabled(boolean status);


}
