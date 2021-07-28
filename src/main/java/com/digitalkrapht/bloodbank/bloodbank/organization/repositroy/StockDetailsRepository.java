package com.digitalkrapht.bloodbank.bloodbank.organization.repositroy;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.StockDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockDetailsRepository extends JpaRepository<StockDetails,Long> {

    @Override
    Optional<StockDetails> findById(Long aLong);

    //    List<StockDetails> findByEnabled(boolean status);
//    Page<StockDetails> findByEnabled(boolean status, Pageable pageable);
//    Long countByEnabled(boolean status);



}
