package com.digitalkrapht.bloodbank.bloodbank.authentication.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtAuthenticationResponse {
    private String message = "success";
    private String accessToken;

    //=== base user
    private String userId;
    private Collection<? extends GrantedAuthority> userRole;
    private Collection<? extends GrantedAuthority> userPrivileges;
    private String firstName;
    private Boolean enabled;
    private Boolean resetPin;
    private String latsName;
    private String mobileNumber;
    private String email;
    private String useGroup;

}
