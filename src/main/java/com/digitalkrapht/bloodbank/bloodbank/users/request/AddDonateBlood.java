package com.digitalkrapht.bloodbank.bloodbank.users.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddDonateBlood {
    private  int donatedQuantity;
    private String bloodDonorId;
    private int bloodGroupId;
    @NotNull
    private Long organisationId;
}
