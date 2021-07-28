package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.CollectionStatus;
import com.digitalkrapht.bloodbank.bloodbank.users.models.BloodRecipient;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserBackOfficeAdmin;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bloodCollection")
@Data
public class BloodCollection extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean enabled =true;
    @Enumerated(EnumType.STRING)
    private CollectionStatus collectionStatus=CollectionStatus.PENDING;
    @OneToOne
    @JoinColumn(name = "bloodRecipientId",referencedColumnName = "userId")
    private BloodRecipient bloodRecipient;
    @OneToOne
    @JoinColumn(name = "organisationAgentId",referencedColumnName = "userId")
    private UserOrganizationAgent organizationAgent;
    @OneToOne
    @JoinColumn(name = "backOfficeAdminId",referencedColumnName = "userId")
    private UserBackOfficeAdmin backOfficeAdmin;
    @OneToOne
    @JoinColumn(name = "bloodRequestId",referencedColumnName = "Id")
    private BloodRequest bloodRequest;




}
