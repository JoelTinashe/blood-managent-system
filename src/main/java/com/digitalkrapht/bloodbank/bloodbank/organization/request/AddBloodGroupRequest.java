package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddBloodGroupRequest {
    @NotNull
    private String bloodGroupName;
    private String shortName;
}
