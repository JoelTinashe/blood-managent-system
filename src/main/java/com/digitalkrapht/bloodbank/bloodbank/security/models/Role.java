package com.digitalkrapht.bloodbank.bloodbank.security.models;

import com.digitalkrapht.bloodbank.bloodbank.utils.auth.BaseAudit;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "sysRoles")
@Data

public class Role  extends BaseAudit {

    @ManyToMany
    @JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private Collection<Permission> permissions;


    @NaturalId
    @Column(length = 60)
    @Enumerated
    private RoleName name;
    @Size(max = 5000)
    private String Description;
    private boolean selfRegEnabled;
}
