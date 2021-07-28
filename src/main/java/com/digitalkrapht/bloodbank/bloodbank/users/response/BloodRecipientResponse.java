package com.digitalkrapht.bloodbank.bloodbank.users.response;

import com.digitalkrapht.bloodbank.bloodbank.organization.response.BloodGroupResponse;
import com.digitalkrapht.bloodbank.bloodbank.organization.response.OrganisationResponse;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.enums.Gender;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BloodRecipientResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    @NotNull
    private String phoneNumber;
    private String address;
    @NotNull
    private String idNumber;
    private boolean active;
    private BloodGroupResponse bloodGroup;
    public List<PermissionResponse> permissions;



}
