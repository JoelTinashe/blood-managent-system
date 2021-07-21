package com.digitalkrapht.bloodbank.bloodbank.organization.response;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.CollectionStatus;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.Quantity;
import com.digitalkrapht.bloodbank.bloodbank.users.response.BloodRecipientResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserBackOfficeAdminResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.response.UserOrganisationAgentResponse;
import lombok.Data;

@Data
public class BloodCollectionResponse {
    private long id;
    private CollectionStatus collectionStatus;
    private UserOrganisationAgentResponse userOrganisationAgent;
    private BloodRecipientResponse bloodRecipient;
    private UserBackOfficeAdminResponse userBackOfficeAdmin;
    private BloodRequestResponse bloodRequest;
    private boolean active;
    private Quantity quantity;
}
