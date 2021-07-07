package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "stockDetailsLog")
@Data
public class StockDetailsLog extends DateAudit {

    @Id
    private int id;
    private String quantity;
    private String unit;
    private StockAdjustmentType stockAdjustmentType;
    private  String stockAdjustmentNotes;
    @OneToOne
    @JoinColumn(name = "bloodGroupId",referencedColumnName = "id")
    private BloodGroup bloodGroup;
    @OneToOne ()
    @JoinColumn(name = "bloodDonorId",referencedColumnName = "userId")
    private UserDonor donors;
    @OneToOne
    @JoinColumn(name = "backAgentId",referencedColumnName = "userId")
    private UserBackOfficeAgent backOfficeAgent;

}
