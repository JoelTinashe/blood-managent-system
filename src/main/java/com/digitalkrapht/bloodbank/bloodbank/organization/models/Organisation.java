package com.digitalkrapht.bloodbank.bloodbank.organization.models;


import com.digitalkrapht.bloodbank.bloodbank.users.models.Gender;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseAudit;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseUserDateAudit;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.UserDateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "organisation", uniqueConstraints = {

        @UniqueConstraint(columnNames = {
                "email"
        })
})

@Data
public class Organisation extends UserDateAudit{
    @Id
    private Long Id;
    private String organisationName;
    private String email;
    private String phoneNumber;
    private String address;
    private String contactPerson;
    private Boolean enabled = true;
    @Enumerated(EnumType.STRING)
    private OrganisationType organisationType  = OrganisationType.NOT_SET;



}
