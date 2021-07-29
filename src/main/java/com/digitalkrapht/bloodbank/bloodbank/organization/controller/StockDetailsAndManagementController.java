package com.digitalkrapht.bloodbank.bloodbank.organization.controller;

import com.digitalkrapht.bloodbank.bloodbank.organization.request.AddStockRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.UpdateStockDetailsRequest;
import com.digitalkrapht.bloodbank.bloodbank.organization.service.OrganisationService;
import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class StockDetailsAndManagementController {
    @Autowired
    OrganisationService organisationService;


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
