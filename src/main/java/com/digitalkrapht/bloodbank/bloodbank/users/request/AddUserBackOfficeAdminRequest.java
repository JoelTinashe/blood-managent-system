package com.digitalkrapht.bloodbank.bloodbank.users.request;

import com.digitalkrapht.bloodbank.bloodbank.users.models.Gender;
import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class AddUserBackOfficeAdminRequest {

    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private Gender gender;
}
