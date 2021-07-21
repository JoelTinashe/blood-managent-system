package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import lombok.Data;

@Data
public class AddBloodCollectionRequest {

    private String organisationAgentId;
    private String bloodRecipientId;
    private String backOfficeAdminId;
    private  long bloodRequestId;
    private long bloodGroupId;


}
