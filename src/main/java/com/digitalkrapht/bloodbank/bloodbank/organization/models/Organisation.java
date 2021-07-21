package com.digitalkrapht.bloodbank.bloodbank.organization.models;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.OrganisationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
