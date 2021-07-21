package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.Quantity;
import com.digitalkrapht.bloodbank.bloodbank.users.models.BloodRecipient;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bloodCollectionLog")
@Data
public class BloodCollectionLog {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private long id;
            private String userOrganizationAgentId;
            private String bloodRecipientId;
            private long bloodGroupId;
            @Enumerated(EnumType.STRING)
            private Quantity quantity= Quantity.APPROVED;


}
