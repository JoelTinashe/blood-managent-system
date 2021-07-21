package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import lombok.Data;

@Data
public class UpdateBloodGroupRequest {
    private long Id;
    private String bloodGroupName;
    private String shortName;
}
