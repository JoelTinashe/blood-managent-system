//package com.digitalkrapht.bloodbank.bloodbank.users.models;
//
//import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodCollection;
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "donorLogs")
//@Data
//public class DonorLogs  extends BloodCollection {
//    @Id
//    private int Id;
//    @OneToOne
//    @JoinColumn(name = "backAgentId",updatable = false,insertable = false)
//    private UserBackOfficeAgent backOfficeAgent;
//    @OneToOne ()
//    @JoinColumn(name = "bloodDonorId",updatable = false,insertable = false)
//    private UserDonor donors;
//    private LocalDate donationDate;
//}
