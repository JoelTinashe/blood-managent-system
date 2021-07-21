package com.digitalkrapht.bloodbank.bloodbank.users.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DonorLogsRequest {

    private String organisationId;
    private String bloodDonorId;
    private LocalDate localDate;


}
