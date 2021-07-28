package com.digitalkrapht.bloodbank.bloodbank.organization.models;

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
            private long bloodRequestId;



}
