package com.digitalkrapht.bloodbank.bloodbank.security.models;


import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "sysPrivileges")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Privilege extends BaseAudit {


    private String name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Permission> permissions;

    public Privilege(final String name) {
        super();
        this.name = name;


    }
}