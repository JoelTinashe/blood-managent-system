package com.digitalkrapht.bloodbank.bloodbank.organization.response;

import lombok.Data;

@Data
public class BloodGroupResponse {
    private int id;
    private String bloodGroupName;
    private boolean active;
    private String shortName;
}
