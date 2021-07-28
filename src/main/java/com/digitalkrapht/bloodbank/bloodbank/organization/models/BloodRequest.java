package com.digitalkrapht.bloodbank.bloodbank.organization.models;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import com.digitalkrapht.bloodbank.bloodbank.users.models.BloodRecipient;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bloodGroupRequest")
@Data
public class BloodRequest extends DateAudit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private BloodStatus bloodStatus=BloodStatus.PENDING;
    private Boolean enabled = true;
    @ManyToOne
    @JoinColumn(name = "organisationAgentId", referencedColumnName = "userId")
    private UserOrganizationAgent userOrganizationAgent;
//    @OneToOne
//    @JoinColumn(name = "bloodGroupId",referencedColumnName = "id")
     private long bloodGroupId;
    @OneToOne
    @JoinColumn(name = "bloodRecipientId",referencedColumnName = "userId")
    private BloodRecipient bloodRecipient;



}
