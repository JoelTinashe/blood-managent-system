package com.digitalkrapht.bloodbank.bloodbank.organization.response;

import lombok.Data;

@Data
public class BloodGroupResponse {
    private Long id;
    private String bloodGroupName;
    private boolean active;
    private String shortName;
}
