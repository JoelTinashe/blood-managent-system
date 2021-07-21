package com.digitalkrapht.bloodbank.bloodbank.users.response;

import com.digitalkrapht.bloodbank.bloodbank.organization.response.OrganisationResponse;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserOrganisationAgentResponse {
    private String address;
    private String userId;
    private String designation;
    private String firstName;
    private String mobileNumber;
    private OrganisationResponse organisation;
    private String lastName;
    private String email;
    private boolean active;
    private Gender gender;
    public List<PermissionResponse> permissions;
}
