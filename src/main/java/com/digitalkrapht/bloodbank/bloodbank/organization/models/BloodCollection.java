package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.users.models.BloodRecipient;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAdmin;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "bloodCollection")
@Data
public class BloodCollection extends DateAudit {
    @Id
    private int id;
    private boolean enabled =true;
    @OneToOne
    @JoinColumn(name = "bloodRecipientId",insertable = false,updatable = false)
    private BloodRecipient bloodRecipient;
    @OneToOne
    @JoinColumn(name = "organisationAgentId",updatable = false,insertable = false)
    private UserOrganizationAgent organizationAgent;
    @OneToOne
    @JoinColumn(name = "backOfficeAdminId")
    private UserBackOfficeAdmin backOfficeAdmin;
    @OneToOne
    @JoinColumn(name = "bloodRequestId",insertable = false,updatable = false)
    private BloodRequest bloodRequest;
    @Enumerated(EnumType.STRING)
    private Quantity quantity= Quantity.APPROVED;



}
