package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import lombok.Data;

@Data
public class UpdateBloodCollectionRequest {
    private int id;
    private String organisationAgentId;
    private String bloodRecipientId;
    private String backOfficeAdminId;
    private  Long bloodRequestId;
}
