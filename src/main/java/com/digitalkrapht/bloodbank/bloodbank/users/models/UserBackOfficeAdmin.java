package com.digitalkrapht.bloodbank.bloodbank.users.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "userBackOfficeAdmin")
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("2")
public class UserBackOfficeAdmin extends User{
}
