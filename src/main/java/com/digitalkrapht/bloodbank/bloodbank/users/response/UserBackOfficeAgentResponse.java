package com.digitalkrapht.bloodbank.bloodbank.users.response;

import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserBackOfficeAgentResponse {


    private String userId;
    private String mobileNumber;
    private String firstName;
    private boolean active;
    private String lastName;
    private String email;
    private Gender gender;
    private List<PermissionResponse> permissions;
}
