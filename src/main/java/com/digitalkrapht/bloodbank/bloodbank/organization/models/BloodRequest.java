package com.digitalkrapht.bloodbank.bloodbank.organization.models;


import com.digitalkrapht.bloodbank.bloodbank.users.models.BloodRecipient;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "bloodGroupRequest")
@Data
public class BloodRequest extends DateAudit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String quantity;
    @Enumerated(EnumType.STRING)
    private BloodStatus bLoodStatus=BloodStatus.NOT_SET;
    private Boolean enabled = true;
    @ManyToOne
    @JoinColumn(name = "organisationAgentId", referencedColumnName = "userId")
    private UserOrganizationAgent userOrganizationAgent;
    @ManyToOne
    @JoinColumn(name = "bloodGroupId",referencedColumnName = "id")
    private BloodGroup bloodGroup;
    @OneToOne
    @JoinColumn(name = "bloodRecipientId",referencedColumnName = "userId")
    private BloodRecipient bloodRecipient;



}
