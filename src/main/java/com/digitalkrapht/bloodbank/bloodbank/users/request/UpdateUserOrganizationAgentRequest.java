package com.digitalkrapht.bloodbank.bloodbank.users.request;

import com.digitalkrapht.bloodbank.bloodbank.users.models.enums.Gender;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserOrganizationAgentRequest {

    private String userId;
    private String address;
    private String designation;
    private String firstName;
    @NotNull
    private String mobileNumber;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private Gender gender;



}
