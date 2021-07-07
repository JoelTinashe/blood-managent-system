package com.digitalkrapht.bloodbank.bloodbank.organization.response;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.OrganisationType;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.Gender;
import lombok.Data;

import java.util.List;

@Data
public class OrganisationResponse {

    private long id;
    private String organisation_Id;
    public String organisationName;
    private String address;
    private String contactPerson;
    private boolean active;
    private String phoneNumber;
    private String email;
    private OrganisationType organisationType;

}
