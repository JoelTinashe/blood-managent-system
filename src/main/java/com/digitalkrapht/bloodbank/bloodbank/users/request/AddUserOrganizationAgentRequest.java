package com.digitalkrapht.bloodbank.bloodbank.users.request;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.Organisation;
import com.digitalkrapht.bloodbank.bloodbank.users.models.Gender;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserOrganizationAgent;
import lombok.Data;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
@Data
public class AddUserOrganizationAgentRequest {

    private String address;
    private String designation;
    private String firstName;
    @NotNull
    private String mobileNumber;
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private Gender gender;
    @NotNull
    private Long organisationId;





}
