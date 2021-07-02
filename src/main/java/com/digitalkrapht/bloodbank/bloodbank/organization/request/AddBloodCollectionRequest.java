package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.Quantity;
import lombok.Data;

@Data
public class AddBloodCollectionRequest {

    private String organisationAgentId;
    private String bloodRecipientId;
    private String backOfficeAdminId;
    private  Long bloodRequestId;


}
