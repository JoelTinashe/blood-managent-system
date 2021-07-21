package com.digitalkrapht.bloodbank.bloodbank.users.models;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.Organisation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "organisationAgent",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "mobileNumber"
        })
})
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("5")
public class UserOrganizationAgent extends User{
    private String mobileNumber;
    private String address;
    private String designation;
    @ManyToOne
    @JoinColumn(name = "organisationId",referencedColumnName = "id")
    private Organisation organisation;





}
