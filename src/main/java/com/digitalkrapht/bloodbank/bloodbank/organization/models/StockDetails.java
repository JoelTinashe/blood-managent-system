package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.StockAdjustmentType;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "stocks")
@Data
public class StockDetails extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String quantity;
    private String unit;
    private StockAdjustmentType stockAdjustmentType=StockAdjustmentType.NOT_SET;
    private  String stockAdjustmentNotes;
    @ManyToOne
    @JoinColumn(name = "bloodDonorId",referencedColumnName = "userId")
    private UserDonor donors;
    @ManyToOne
    @JoinColumn(name = "backAgentId",referencedColumnName = "userId")
    private UserBackOfficeAgent backOfficeAgent;
//    @ManyToOne
//    @JoinColumn(name = "bloodGroupId", referencedColumnName = "Id")
//    private BloodGroup bloodGroup;
      private Long bloodGroupId;



}
