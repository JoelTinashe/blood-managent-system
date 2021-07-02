package com.digitalkrapht.bloodbank.bloodbank.users.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "userBackOfficeAgent",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "mobileNumber"
        })
})
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("3")
public class UserBackOfficeAgent extends User{
    private String mobileNumber;


}
