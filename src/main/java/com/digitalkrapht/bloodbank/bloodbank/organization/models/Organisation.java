package com.digitalkrapht.bloodbank.bloodbank.organization.models;


import com.digitalkrapht.bloodbank.bloodbank.users.models.Gender;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseAudit;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseUserDateAudit;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "organisation", uniqueConstraints = {

        @UniqueConstraint(columnNames = {
                "id", "email"
        })
})

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Organisation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String organisationName;
    private String email;
    private String phoneNumber;
    private String address;
    private String contactPerson;
    private Boolean enabled = true;
    @Enumerated(EnumType.STRING)
    private OrganisationType organisationType  = OrganisationType.NOT_SET;



}
