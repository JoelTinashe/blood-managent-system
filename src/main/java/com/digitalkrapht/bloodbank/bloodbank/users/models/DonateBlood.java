package com.digitalkrapht.bloodbank.bloodbank.users.models;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.Organisation;
import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "donatedBlood")
@Data
public class DonateBlood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "bloodDonorId",referencedColumnName = "userId")
    private UserDonor donors;

    @ManyToOne
    @JoinColumn(name = "organisationId",referencedColumnName = "id")
    private Organisation organisation;

    private int bloodGroupId;

    private int  donatedQuantity;
}
