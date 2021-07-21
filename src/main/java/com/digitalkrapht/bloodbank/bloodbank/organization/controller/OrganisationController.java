package com.digitalkrapht.bloodbank.bloodbank.organization.controller;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.CollectionStatus;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.service.OrganisationService;
import com.digitalkrapht.bloodbank.bloodbank.users.request.AddUserOrganizationAgentRequest;
import com.digitalkrapht.bloodbank.bloodbank.users.request.UpdateUserOrganizationAgentRequest;
import com.digitalkrapht.bloodbank.bloodbank.users.service.UserService;
import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class OrganisationController {
    @Autowired
    OrganisationService organisationService;

    @Autowired
    UserService userService;

//////////////////////////////////////////////////////Organisation/////////////////////////////////////////////////////
    @PostMapping("/organisation/create")
    @Operation(description="Create Organisation ")
    public ResponseEntity createOrganisation(@Valid @RequestBody AddOrganisationRequest request) {
        return organisationService.createOrganisation(request);
    }

    @GetMapping("/organisation/view/all")
    public ResponseEntity getAllOrganisations(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return organisationService.getAllOrganisations(page, size);
    }


    @PutMapping("/organisation/update")
    @Operation(description="Update organisation ")
    public ResponseEntity updateOrganisation(@Valid @RequestBody UpdateOrganisationRequest request) {

        return organisationService.updateOrganisation(request);
    }

    @GetMapping("/organisation/activation/{Id}/{status}")
    @Operation(description="activate or deactivate Organisation")
    public ResponseEntity changeOrganisationStatus(@PathVariable long Id, @PathVariable Boolean status) {
        return organisationService.changeOrganisationStatus(Id, status);
    }



    @GetMapping("/organisation/view/active")
    public ResponseEntity getActiveOrganisation() {
        return organisationService.getActiveOrganisation();
    }

    ////////////////////////////////////////////Organisation Agents/////////////////////////////////////////////////////////
    @PostMapping("/organisationAgent/create")
    @Operation(description="Create  organisation Agent")
    public ResponseEntity createOrganisationAgent(@Valid @RequestBody AddUserOrganizationAgentRequest request) {
        return userService.createOrganisationAgent(request);
    }
    @PutMapping("/organisationAgent/update")
    @Operation(description="Update Organisation Agents ")
    public ResponseEntity updateOrganisationAgent(@Valid @RequestBody UpdateUserOrganizationAgentRequest request) {

        return userService.updateOrganisationAgent(request);
    }
    @GetMapping("/organisationAgent/activation/{userId}/{status}")
    @Operation(description="activate or deactivate organisation Agents")
    public ResponseEntity changeOrganisationAgentStatus(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.changeOrganisationAgentStatus(userId, status);
    }
    @GetMapping("/organisationAgent/view/active")
    public ResponseEntity getActiveOrganisationAgents() {
        return userService.getActiveOrganisationAgents();
    }
    @GetMapping("/organisationAgent/view/all")
    public ResponseEntity getAllOrganisationAgents(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                   @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getAllOrganisationAgent(page, size);
    }
    @GetMapping("/organisationAgent/view/byStatus/{status}")
    public ResponseEntity getorganisationAgentByStatus(@PathVariable boolean status,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getOrganisationAgentByStatus(status,page, size);
    }
    @GetMapping("/organisationAgent/view/byUserId/{userId}")
    public ResponseEntity getOrganisationAgentById(@PathVariable String userId) {
        return userService.getOrganisationById(userId);
    }














}