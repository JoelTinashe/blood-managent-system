package com.digitalkrapht.bloodbank.bloodbank.security.user;

import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.users.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserPrincipal implements UserDetails {

    private String userId;
    private String firstName;
    private String lastName;
    private String tokenHash;
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> privileges;

    private Collection<Role> roles;

    public UserPrincipal(String userId, String firstName, String lastName, String tokenHash, String username, String email, String password, Collection<? extends GrantedAuthority> privileges,
                         Collection<Role> roles) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tokenHash = tokenHash;
        this.username = username;
        this.email = email;
        this.password = password;
        this.privileges = privileges;
        this.roles = roles;
    }

    public static UserPrincipal create(User user) {


        return new UserPrincipal(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getTokenHash(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user.getRoles()),
                user.getRoles()
        );
    }



    private static Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private static final List<String> getPrivileges(final Collection<Role> roles) {
        final List<String> privileges = new CopyOnWriteArrayList<>();
        for (final Role role : roles) {
            privileges.add(role.getName().toString());
        }


        return privileges;
    }

    private static final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
        final List<GrantedAuthority> authorities = new CopyOnWriteArrayList<GrantedAuthority>();
        for (final String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }


    public String getEmail() {
        return email;
    }


    public Collection<? extends GrantedAuthority> getPrivileges() {
        return privileges;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return privileges;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId);
    }


}
