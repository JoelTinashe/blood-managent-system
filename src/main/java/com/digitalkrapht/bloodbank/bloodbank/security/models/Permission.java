package com.digitalkrapht.bloodbank.bloodbank.security.models;

import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;


@Data
@Entity
@Table(name = "sysPermissions")
@AllArgsConstructor
@NoArgsConstructor

public class Permission extends BaseAudit {


    private String name;

    @ManyToMany(mappedBy = "permissions")
    private Collection<Role> roles;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}
