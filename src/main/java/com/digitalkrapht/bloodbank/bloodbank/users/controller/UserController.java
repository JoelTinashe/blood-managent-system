package com.digitalkrapht.bloodbank.bloodbank.users.controller;

import com.digitalkrapht.bloodbank.bloodbank.security.user.UserPrincipal;
import com.digitalkrapht.bloodbank.bloodbank.users.request.*;
import com.digitalkrapht.bloodbank.bloodbank.users.service.UserService;
import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    //////////////////////////Blood Systems Admins////////////////////////////////////////////////////////////////////
    @PostMapping("/backOfficeAdmin/create")
    @Operation(description="Create Blood Back Admin")
    public ResponseEntity addBackAdmin(@Valid @RequestBody AddUserBackOfficeAdminRequest request) {
        return userService.addBackOfficeAdmin(request);
    }
    @PutMapping("/backOfficeAdmin/update")
    @Operation(description="Update Back Office Admin")
    public ResponseEntity updateBackOfficeAdmin(@Valid @RequestBody UpdateUserBackOfficeAdminRequest request) {

        return userService.updateBackOfficeAdmin(request);
    }
    @GetMapping("/backOfficeAdmin/activation/{userId}/{status}")
    @Operation(description="activate or deactivate backOffice Admins")
    public ResponseEntity changeBackOfficeAdminStatus(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.changeBackOfficeAdminStatus(userId, status);
    }
    @GetMapping("/backOfficeAdmin/view/active")
    public ResponseEntity getActiveBackOfficeAdmins() {
//       UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getActiveBackOfficeAdmins();
    }
    @GetMapping("/backOfficeAdmin/view/all")
    public ResponseEntity getAllBackOfficeAdmins(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                           @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getAllBackOfficeAdmins(page, size);
    }
    @GetMapping("/backOfficeAdmin/view/byStatus/{status}")
    public ResponseEntity getBackOfficeByStatus(@PathVariable boolean status,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getBackOfficeAdminsByStatus(status,page, size);
    }

    @GetMapping("/backOfficeAdmin/view/byUserId/{userId}")
    public ResponseEntity getBackOfficeAdminById(@PathVariable String userId) {
        return userService.getBackAdminById(userId);
    }
//////////////////////////////////////////////////////Agents/////////////////////////////////////////////////////
    @PostMapping("/backOfficeAgent/create")
    @Operation(description="Create Blood Back Agent")
    public ResponseEntity createBackAgent(@Valid @RequestBody AddUserBackOfficeAgentRequest request) {
        return userService.createBackOfficeAgent(request);
    }
    @PutMapping("/backOfficeAgents/update")
    @Operation(description="Update Back Agents ")
    public ResponseEntity updateBackOfficeAgent(@Valid @RequestBody UpdateUserBackOfficeAgentRequest request) {

        return userService.updateBackOfficeAgent(request);
    }
    @GetMapping("/backOfficeAgent/activation/{userId}/{status}")
    @Operation(description="activate or deactivate backOffice Agents")
    public ResponseEntity changeBackOfficeAgentStatus(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.changeBackOfficeAgentStatus(userId, status);
    }
    @GetMapping("/backOfficeAgent/view/active")
    public ResponseEntity getActiveBackOfficeAgents() {
        return userService.getActiveBackOfficeAgents();
    }
    @GetMapping("/backOfficeAgent/view/all")
    public ResponseEntity getAllBackOfficeAgents(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getAllBackOfficeAgents(page, size);
    }
    @GetMapping("/backOfficeAgent/view/byStatus/{status}")
    public ResponseEntity getBackOfficeAgentByStatus(@PathVariable boolean status,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getBackOfficeAgentsByStatus(status,page, size);
    }
    @GetMapping("/backOfficeAgent/view/byUserId/{userId}")
    public ResponseEntity getBackOfficeAgentById(@PathVariable String userId) {
        return userService.getBackAgentById(userId);
    }

    ////////////////////////////////customer or Blood Donor////////////////////////////////////////////////////////////

    @PostMapping("/donor/create")
    @Operation(description="Create Blood donor customer")
    public ResponseEntity createDonor(@Valid @RequestBody AddUserDonorRequest request) {
        return userService.createUserDonor(request);
    }
    @PutMapping("/donor/update")
    @Operation(description="Update donors")
    public ResponseEntity updateDonor(@Valid @RequestBody UpdateUserDonorRequest request) {

        return userService.updateCustomer(request);
    }
    @GetMapping("/donor/activation/{userId}/{status}")
    @Operation(description="activate or deactivate donors")
    public ResponseEntity changeDonorStatus(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.changeDonorStatus(userId, status);
    }
    @GetMapping("/customerDonor/view/active")
    public ResponseEntity getActiveDonors() {
        return userService.getActiveCustomers();
    }
    @GetMapping("/donor/view/all")
    public ResponseEntity getAllDonors(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getAllCustomers(page, size);
    }
    @GetMapping("/donor/view/byStatus/{status}")
    public ResponseEntity getDonorsByStatus(@PathVariable boolean status,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                     @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getCustomerDonorByStatus(status,page, size);
    }
    @GetMapping("/donor/view/byUserId/{userId}")
    public ResponseEntity getDonorById(@PathVariable String userId) {
        return userService.getCustomerDonorById(userId);
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
    //////////////////////////////////////BloodRecipient/////////////////////////////
    @PostMapping("/bloodRecipient/create")
    @Operation(description="Create  Blood Recipient")
    public ResponseEntity createBloodRecipient(@Valid @RequestBody BloodRecipientRequest request) {
        return userService.createBloodRecipient(request);
    }
    @PutMapping
            ("/bloodRecipient/update")
    @Operation(description="Update Blood Recipient ")
    public ResponseEntity updateBloodRecipient(@Valid @RequestBody UpdateBloodRecipientRequest request) {

        return userService.updateBloodRecipient(request);
    }
    @GetMapping("/bloodRecipient/activation/{userId}/{status}")
    @Operation(description="activate or deactivate Blood Recipient")
    public ResponseEntity changeBloodRecipientStatus(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.changeBloodRecipientStatus(userId, status);
    }
    @GetMapping("/bloodRecipient/view/active")
    public ResponseEntity getActiveBloodRecipient() {
        return userService.getActiveRecipients();
    }
    @GetMapping("/bloodRecipient/view/all")
    public ResponseEntity getAllBloodRecipient(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                   @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getAllBloodRecipients(page, size);
    }
    @GetMapping("/bloodRecipient/view/byStatus/{status}")
    public ResponseEntity getBloodRecipientByStatus(@PathVariable boolean status,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getBloodRecipientByStatus(status,page, size);
    }
    @GetMapping("/bloodRecipient/view/byUserId/{userId}")
    public ResponseEntity getBloodRecipientById(@PathVariable String userId) {
        return userService.getRecipientById(userId);
    }





}
