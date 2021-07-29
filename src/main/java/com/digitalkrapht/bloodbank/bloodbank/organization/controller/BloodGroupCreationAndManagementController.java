package com.digitalkrapht.bloodbank.bloodbank.organization.controller;


import com.digitalkrapht.bloodbank.bloodbank.organization.request.AddBloodGroupRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.UpdateBloodGroupRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.service.OrganisationService;
import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class BloodGroupCreationAndManagementController {

    @Autowired
    OrganisationService organisationService;




////////////////////////////// Blood group Controller/////////////////////////////////////////////////////////////////

    @PostMapping("/bloodGroup/create")
    @Operation(description="Create blood group ")
    public ResponseEntity createBloodGroup(@Valid @RequestBody AddBloodGroupRequest request) {
        return organisationService.createBloodGroup(request);
    }
    @PutMapping("/bloodGroup/update")
    @Operation(description="Update bloodGroup ")
    public ResponseEntity updateBloodGroup(@Valid @RequestBody UpdateBloodGroupRequest request) {

        return organisationService.updateBloodGroup(request);
    }
    @GetMapping("/bloodGroup/activation/{Id}/{status}")
    @Operation(description="activate or deactivate Blood Group")
    public ResponseEntity changeBloodGroupStatus(@PathVariable Integer Id, @PathVariable Boolean status) {
        return organisationService.changeBloodGroupStatus(Id, status);
    }
    @GetMapping("/bloodGroup/view/active")
    public ResponseEntity getActiveBloodGroup() {
        return organisationService.getActiveBloodGroup();
    }
    @GetMapping("/bloodGroup/view/all")
    public ResponseEntity getAllBloodGroup(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                           @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return organisationService.getAllBloodGroup(page, size);
    }


}
