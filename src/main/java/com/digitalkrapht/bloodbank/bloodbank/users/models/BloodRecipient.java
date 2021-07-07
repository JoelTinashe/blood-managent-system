package com.digitalkrapht.bloodbank.bloodbank.users.models;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "recipients")

@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("6")
@PrimaryKeyJoinColumn(name = "userId")
@Inheritance(strategy = InheritanceType.JOINED)
public class BloodRecipient extends User{
        @NotNull
        private String phoneNumber;
        private String address;
        @NotNull
        private String idNumber;
        @OneToOne
        @JoinColumn(name = "bloodGroupId",referencedColumnName = "id")
        private BloodGroup bloodGroup;

}
