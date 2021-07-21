package com.digitalkrapht.bloodbank.bloodbank.users.response;

import com.digitalkrapht.bloodbank.bloodbank.organization.response.BloodGroupResponse;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.enums.Gender;
import lombok.Data;

import java.util.List;
@Data
public class UserDonorResponse {


    private String firstName;
    private String userId;
    private boolean active;
    private String lastName;
    private String mobileNumber;
    private String email;
    private Gender gender;
    private BloodGroupResponse bloodGroup;
    private boolean donorStatus;
    private int age;
    private String nationalID;
    private List<PermissionResponse> permissions;
}
