package com.digitalkrapht.bloodbank.bloodbank.users.request;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import com.digitalkrapht.bloodbank.bloodbank.users.models.Gender;
import lombok.Data;


import javax.validation.constraints.NotNull;

@Data
public class BloodRecipientRequest {

    private String firstName;
    private String lastName;
    private String email;
    @NotNull
    private String phoneNumber;
    private String address;
    @NotNull
    private String idNumber;
    private int bloodGroupId;
    @NotNull
    private Gender gender;

}