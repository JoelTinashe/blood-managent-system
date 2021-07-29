package com.digitalkrapht.bloodbank.bloodbank.organization.controller;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.AddBloodCollectionRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.AddBloodRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.UpdateBloodRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.service.OrganisationService;
import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class BloodRequestController {

    @Autowired
    OrganisationService organisationService;
    ////////////////////////////Blood Request/////////////////////////////////////////////////////////////
    @PostMapping("/bloodRequest/create")
    @Operation(description="Create blood Request ")
    public ResponseEntity createBloodRequest(@Valid @RequestBody AddBloodRequest request) {
        return organisationService.createBloodRequest(request);
    }

    @PutMapping("/bloodRequest/update")
    @Operation(description="Update Blood request ")
    public ResponseEntity updateBloodRequest(@Valid @RequestBody UpdateBloodRequest request) {

        return organisationService.updateBloodRequest(request);
    }

    @GetMapping("/bloodRequest/activation/{Id}/{status}")
    @Operation(description="activate or deactivate Blood request")
    public ResponseEntity changeBloodRequestActivateOrDeactivate(@PathVariable long Id, @PathVariable Boolean status) {
        return organisationService.changeBloodRequestActivateOrDeactivate(Id, status);
    }

    @GetMapping("/bloodRequest/changeStatus/{Id}/{status}")
    @Operation(description="change Blood request Status")
    public ResponseEntity changeBloodRequestStatus(@PathVariable long Id, @PathVariable BloodStatus status) {
        return organisationService.changeBloodRequestStatus(Id, status);
    }


    @GetMapping("/bloodRequest/view/active")
    public ResponseEntity getActiveBloodRequest() {
        return organisationService.getActiveBloodRequest();
    }

    @GetMapping("/bloodRequests/view/all")
    public ResponseEntity getAllBloodRequest(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                             @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return organisationService.getAllBloodRequests(page, size);
    }

}
