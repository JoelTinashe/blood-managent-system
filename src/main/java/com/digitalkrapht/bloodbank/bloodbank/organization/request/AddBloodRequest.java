package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddBloodRequest {
    @NotNull
    private String quantity;
    @NotNull
    private String organisationAgentId;
    @NotNull
    private long bloodGroupId;
    @NotNull
    private String bloodRecipientId;
}
