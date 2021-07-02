package com.digitalkrapht.bloodbank.bloodbank.organization.response;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodStatus;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import com.digitalkrapht.bloodbank.bloodbank.users.response.BloodRecipientResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserOrganisationAgentResponse;
import lombok.Data;

@Data
public class BloodRequestResponse {
    private Long Id;
    private boolean active;
    private String quantity;
    private BloodStatus bLoodStatus;
    private BloodGroupResponse bloodGroup;
    private BloodRecipientResponse bloodRecipient;
    private UserOrganisationAgentResponse userOrganizationAgent;

}
