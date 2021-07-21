package com.digitalkrapht.bloodbank.bloodbank.organization.response;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.OrganisationType;
import lombok.Data;

@Data
public class OrganisationResponse {

    private long id;
    public String organisationName;
    private String address;
    private String contactPerson;
    private boolean active;
    private String phoneNumber;
    private String email;
    private OrganisationType organisationType;

}
