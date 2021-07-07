package com.digitalkrapht.bloodbank.bloodbank.users.models;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "userDonors",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "mobileNumber"
        })
})
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("1")
public class UserDonor extends User {

    private String mobileNumber;
    private String nationalID;
    private boolean approved=true;
    private int age;

    @OneToOne
    @JoinColumn(name = "bloodGroupId",referencedColumnName = "id")
    private BloodGroup bloodGroup;


}
