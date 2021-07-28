package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import lombok.Data;

@Data
public class AddBloodCollectionRequest {

    private String organisationAgentId;
    private String bloodRecipientId;
    private String backOfficeAdminId;
    private  long bloodRequestId;
    private BloodStatus bloodStatus;

}
