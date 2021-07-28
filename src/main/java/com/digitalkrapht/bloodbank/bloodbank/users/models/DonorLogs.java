package com.digitalkrapht.bloodbank.bloodbank.users.models;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodCollection;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "donorLogs")
@Data
public class DonorLogs {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    private long OrganizationId;
    private String donorId;
    private LocalDate local;
    private String quantity;


}
