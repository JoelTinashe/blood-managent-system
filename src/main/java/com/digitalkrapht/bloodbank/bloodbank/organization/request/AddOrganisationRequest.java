package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.OrganisationType;
import com.digitalkrapht.bloodbank.bloodbank.users.models.Gender;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class AddOrganisationRequest {
    private String organisationName;
    @NotNull
    private String email;
    @NotNull
    private OrganisationType organisationType;
    @NotNull
    private String phoneNumber;
    private String contactPerson;
    private String address;
}
