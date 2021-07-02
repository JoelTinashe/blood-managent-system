package com.digitalkrapht.bloodbank.bloodbank.users.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "userSystemAdmin")
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "userId")
@DiscriminatorValue("4")
public class UserSystemAdmin extends User{


}
