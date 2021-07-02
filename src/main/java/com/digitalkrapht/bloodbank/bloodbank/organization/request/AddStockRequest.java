package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.StockAdjustmentType;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
public class AddStockRequest {
    private String quantity;
    private String unit;
    private StockAdjustmentType stockAdjustmentType;
    private  String stockAdjustmentNotes;
    @NotNull
    private String bloodDonorId;
    @NotNull
    private String backAgentId;
    @NotNull
    private int bloodGroupId;

    ;

}
