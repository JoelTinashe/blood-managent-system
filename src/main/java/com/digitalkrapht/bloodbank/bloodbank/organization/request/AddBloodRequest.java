package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddBloodRequest {
    @NotNull
    private int quantity;
    @NotNull
    private String organisationAgentId;
    @NotNull
    private int bloodGroupId;
    @NotNull
    private String bloodRecipientId;

    private BloodStatus bloodStatus;
}
