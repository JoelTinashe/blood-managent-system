package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.StockAdjustmentType;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "stocksLog")
@Data
public class StockDetails extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int donatedQuantity;
    private String unit;
    @Enumerated(EnumType.STRING)
    private StockAdjustmentType stockAdjustmentType=StockAdjustmentType.NOT_SET;
    private  String stockAdjustmentNotes;

    private String donorId;
    @ManyToOne
    @JoinColumn(name = "backAgentId",referencedColumnName = "userId")
    private UserBackOfficeAgent backOfficeAgent;
    private long bloodGroupId;





}
