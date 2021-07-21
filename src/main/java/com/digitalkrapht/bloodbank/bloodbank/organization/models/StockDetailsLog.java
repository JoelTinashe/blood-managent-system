package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.StockAdjustmentType;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Entity
@Table(name = "stockDetailsLog")
@Data

public class StockDetailsLog extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String quantity;
    private String unit;
    private StockAdjustmentType stockAdjustmentType=StockAdjustmentType.NOT_SET;
    private  String stockAdjustmentNotes;
    private Long bloodGroupId;
    private String donorId;
    private String backOfficeAgentId;



}
