package com.digitalkrapht.bloodbank.bloodbank.organization.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.OrganisationType;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UpdateOrganisationRequest {

   private long id;
    @NotNull
    private String email;
    @NotNull
    private OrganisationType organisationType;
    @NotNull
    private String phoneNumber;
    private String contactPerson;
    private String address;
    private String organisationName;
}
