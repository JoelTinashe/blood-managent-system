package com.digitalkrapht.bloodbank.bloodbank.organization.models;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "bloodGroup")
@Data
public class BloodGroup extends DateAudit {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)

       private Integer bloodId;
        private String bloodGroupName;
        private boolean enabled = true;
        private String shortName;
        private int totalQuantity;

}
