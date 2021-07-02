package com.digitalkrapht.bloodbank.bloodbank.authentication.response;

import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
@Data


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSummary {

    private List<PermissionResponse> permissions;
    //== users details
    private String userId;
    private String firstName;
    private String mobileNumber;
    private String username;
    private Boolean enabled = true;
    private Boolean resetPin = false;
    private String lastName;
    private String email;
    private String userGroup;
    private String avatar = "default";
    private String gender;
    private String bloodGroup;
    private int age;
    private Collection<? extends GrantedAuthority> userRoles;
    private Collection<? extends GrantedAuthority> privileges;
    private String message = "success";
    private String accessToken;



}
