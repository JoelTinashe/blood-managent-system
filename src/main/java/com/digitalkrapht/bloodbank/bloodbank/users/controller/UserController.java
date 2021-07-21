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
        UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.addBackOfficeAdmin(request, currentUSer.getUserId());
    }
    @PutMapping("/backOfficeAdmin/update")
    @Operation(description="Update Back Office Admin")
    public ResponseEntity updateBackOfficeAdmin(@Valid @RequestBody UpdateUserBackOfficeAdminRequest request) {
        UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.updateBackOfficeAdmin(request, currentUSer.getUserId());
    }
    @GetMapping("/backOfficeAdmin/activation/{userId}/{status}")
    @Operation(description="activate or deactivate backOffice Admins")
    public ResponseEntity changeBackOfficeAdminStatus(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.changeBackOfficeAdminStatus(userId, status);
    }
    @GetMapping("/backOfficeAdmin/view/active")
    public ResponseEntity getActiveBackOfficeAdmins() {
        return userService.getActiveBackOfficeAdmins();
    }
    @GetMapping("/backOfficeAdmin/view/all")
    public ResponseEntity getAllBackOfficeAdmins(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                           @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getAllBackOfficeAdmins(page, size,currentUSer.getUserId());
    }
    @GetMapping("/backOfficeAdmin/view/byStatus/{status}")
    public ResponseEntity getBackOfficeByStatus(@PathVariable boolean status,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getBackOfficeAdminsByStatus(status,page, size);
    }

    @GetMapping("/backOfficeAdmin/view/byUserId/{userId}")
    public ResponseEntity getBackOfficeAdminById(@PathVariable String userId) {
        UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getBackAdminById(userId,currentUSer.getUserId());
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



}
