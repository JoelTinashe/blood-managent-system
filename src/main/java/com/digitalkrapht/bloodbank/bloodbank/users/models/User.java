package com.digitalkrapht.bloodbank.bloodbank.users.models;


import com.digitalkrapht.bloodbank.bloodbank.security.models.Permission;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Privilege;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.users.models.enums.Gender;
import com.digitalkrapht.bloodbank.bloodbank.utils.auth.UserDateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})

@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("0")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends UserDateAudit {
        @Id
        private String userId;
        private String firstName;
        private String lastName;
        private LocalDateTime lastLogin;
        private long loginCount;
        private Boolean enabled = true;
        private Boolean resetPin = true;
        private String email;
        @Enumerated(EnumType.STRING)
        private Gender gender = Gender.NOT_SET;
        //=== security
        private String username;
        private String password;
        private String tokenHash = "default";
        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
        private Collection<Role> roles;

        @ManyToMany
        @JoinTable(name = "user_privileges", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
                inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
        private Collection<Privilege> privileges;

        @ManyToMany
        @JoinTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"),
                inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
        private Collection<Permission> permissions;



}
