package com.digitalkrapht.bloodbank.bloodbank.organization.controller;


import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.CollectionStatus;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.AddBloodCollectionRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.UpdateBloodCollectionRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.service.OrganisationService;
import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class BloodCollectionController {
    @Autowired
    OrganisationService organisationService;


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
    public ResponseEntity changeBloodCollectionActiveOrDeactivate(@PathVariable Integer Id, @PathVariable Boolean status) {
        return organisationService.changeBloodCollectionActiveOrDeactivate(Id, status);
    }

    @GetMapping("/bloodColletion/changeStatus/{Id}/{status}")
    @Operation(description="change  Blood Collection Status")
    public ResponseEntity changeBloodRequestStatus(@PathVariable Integer Id, @PathVariable CollectionStatus status) {
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


}
