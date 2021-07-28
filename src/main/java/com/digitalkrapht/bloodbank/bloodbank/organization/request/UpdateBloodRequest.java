package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateBloodRequest {

    private  long Id;
    @NotNull
    private int quantity;
    @NotNull
    private String organisationAgentId;
    @NotNull
    private int bloodGroupId;
    @NotNull
    private String bloodRecipientId;

}
