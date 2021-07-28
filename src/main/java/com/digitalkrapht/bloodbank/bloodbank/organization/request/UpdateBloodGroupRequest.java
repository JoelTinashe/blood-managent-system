package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import lombok.Data;

@Data
public class UpdateBloodGroupRequest {
    private int bloodId;
    private String bloodGroupName;
    private String shortName;
}
