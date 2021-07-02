package com.digitalkrapht.bloodbank.bloodbank.organization.controller;

import com.digitalkrapht.bloodbank.bloodbank.organization.request.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.service.OrganisationService;
import com.digitalkrapht.bloodbank.bloodbank.users.request.*;
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

//////////////////////////////////////////////////////Organisation/////////////////////////////////////////////////////
    @PostMapping("/organisation/create")
    @Operation(description="Create Organisation ")
    public ResponseEntity createOrganisation(@Valid @RequestBody AddOrganisationRequest request) {
        return organisationService.createOrganisation(request);
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

    @GetMapping("/organisation/view/byStatus/{status}")
    public ResponseEntity getOrganisationByStatus(@PathVariable boolean status,@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return organisationService.getOrganisationByStatus(status,page, size);
    }



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
    public ResponseEntity changeBloodRequestStatus(@PathVariable long Id, @PathVariable Boolean status) {
        return organisationService.changeBloodRequestStatus(Id, status);
    }

    @GetMapping("/bloodRequest/view/active")
    public ResponseEntity getActiveBloodRequest() {
        return organisationService.getActiveBloodRequest();
    }

    @GetMapping("/bloodRequests/view/all")
    public ResponseEntity getAllBackOfficeAgents(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return organisationService.getAllBloodRequests(page, size);
    }


/////////////////////////////////////////////// Blood Collection//////////////////////////////////////////////////////////

    @PostMapping("/bloodCollection/create")
    @Operation(description="Create blood Collection ")
    public ResponseEntity createBloodCollection(@Valid @RequestBody AddBloodCollectionRequest request) {
        return organisationService.createBloodCollection(request);
    }

    @PutMapping("/bloodColletion/update")
    @Operation(description="Update blood collection ")
    public ResponseEntity updateBloodCollection(@Valid @RequestBody UpdateBloodCollectionRequest request) {

        return organisationService.updateBloodCollect(request);
    }

    @GetMapping("/bloodColletion/activation/{Id}/{status}")
    @Operation(description="activate or deactivate Blood Collection")
    public ResponseEntity changeBloodRequestStatus(@PathVariable Integer Id, @PathVariable Boolean status) {
        return organisationService.changeBloodCollectionStatus(Id, status);
    }

    @GetMapping("/bloodCollection/view/active")
    public ResponseEntity getActiveBloodCollection() {
        return organisationService.getActiveBloodCollection();
    }

    @GetMapping("/bloodCollection/view/all")
    public ResponseEntity getAllBloodCollections(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size)
    {
        return organisationService.getAllBloodCollections(page, size);
    }


/////////////////////////stock details//////////////////////////////////////////////////////////////////


    @PostMapping("/stockDetails/create")
    @Operation(description="Create blood Stock Details ")
    public ResponseEntity createStockDetails(@Valid @RequestBody AddStockRequest request) {
        return organisationService.createStockDetails(request);
    }

    @PutMapping("/stockDetails/update")
    @Operation(description="Update blood Stock Details ")
    public ResponseEntity updateBloodStockDetails(@Valid @RequestBody UpdateStockDetailsRequest request) {

        return organisationService.updateStockDetails(request);
    }

    @GetMapping("/bloodStockdetails/view/all")
    public ResponseEntity getAllStockDetails(@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size)
    {
        return organisationService.getAllStockDetails(page, size);
    }


}