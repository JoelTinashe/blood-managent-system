package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAdmin;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock")
@Data
public class StockDetails extends DateAudit {
    @Id
    private int id;
    private String quantity;
    private String unit;
    private StockAdjustmentType stockAdjustmentType=StockAdjustmentType.NOT_SET;
    private  String stockAdjustmentNotes;
    @ManyToOne
    @JoinColumn(name = "bloodGroupId",referencedColumnName = "id")
    private BloodGroup bloodGroup;
    @ManyToOne
    @JoinColumn(name = "bloodDonorId",referencedColumnName = "userId")
    private UserDonor donors;
    @ManyToOne
    @JoinColumn(name = "backAgentId",referencedColumnName = "userId")
    private UserBackOfficeAgent backOfficeAgent;


}
