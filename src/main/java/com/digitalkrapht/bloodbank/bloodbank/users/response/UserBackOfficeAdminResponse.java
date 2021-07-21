package com.digitalkrapht.bloodbank.bloodbank.users.response;

import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserBackOfficeAdminResponse {

    private String firstName;
    private String userId;
    private boolean active;
    private String lastName;
    private String email;
    private Gender gender;
    public List<PermissionResponse> permissions;
}
