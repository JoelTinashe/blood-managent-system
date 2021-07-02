package com.digitalkrapht.bloodbank.bloodbank.users.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DonorLogsRequest {

    private LocalDate donationDate ;
    private String backAgentId;
    private String bloodDonorId;


}
