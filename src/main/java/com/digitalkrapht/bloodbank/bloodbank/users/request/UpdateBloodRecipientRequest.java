package com.digitalkrapht.bloodbank.bloodbank.users.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateBloodRecipientRequest {
    private String userId;
    private String firstName;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    private String address;


}
