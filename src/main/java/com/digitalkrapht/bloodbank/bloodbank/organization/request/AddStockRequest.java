package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.StockAdjustmentType;
import lombok.Data;

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
    private long bloodGroupId;



}
