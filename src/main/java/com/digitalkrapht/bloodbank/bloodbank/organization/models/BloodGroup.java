package com.digitalkrapht.bloodbank.bloodbank.organization.models;

import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseUserDateAudit;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.DateAudit;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;


@Entity
@Table(name = "bloodGroup")
@Data
public class BloodGroup extends DateAudit {

       @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
       private int id;
        private String bloodGroupName;
        private boolean enabled =true;
        private String shortName;






}
