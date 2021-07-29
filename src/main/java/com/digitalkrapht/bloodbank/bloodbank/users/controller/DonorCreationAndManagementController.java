package com.digitalkrapht.bloodbank.bloodbank.users.controller;

import com.digitalkrapht.bloodbank.bloodbank.security.user.UserPrincipal;
import com.digitalkrapht.bloodbank.bloodbank.users.request.AddDonateBlood;
import com.digitalkrapht.bloodbank.bloodbank.users.request.AddUserDonorRequest;
import com.digitalkrapht.bloodbank.bloodbank.users.request.UpdateUserDonorRequest;
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
public class DonorCreationAndManagementController {

    @Autowired
    UserService userService;
    ////////////////////////////////customer or Blood Donor////////////////////////////////////////////////////////////

    @PostMapping("/donor/create")
    @Operation(description="Create Blood donor customer")
    public ResponseEntity createDonor(@Valid @RequestBody AddUserDonorRequest request) {
        UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.createUserDonor(request, currentUSer.getUserId());
    }
    @PutMapping("/donor/update")
    @Operation(description="Update donors")
    public ResponseEntity updateDonor(@Valid @RequestBody UpdateUserDonorRequest request) {
        UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userService.updateCustomer(request,currentUSer.getUserId());
    }
    @GetMapping("/donor/activation/{userId}/{status}")
    @Operation(description="activate or deactivate donors")
    public ResponseEntity changeDonorStatus(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.changeDonorStatus(userId, status);
    }


    @GetMapping("/donor/approveOrReject/{userId}/{status}")
    @Operation(description="approve or reject donors")
    public ResponseEntity approveOrRejectDonor(@PathVariable String userId, @PathVariable Boolean status) {
        return userService.approveOrRejectDonorStatus(userId, status);
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

    @PostMapping("/donor/donateBlood")
    @Operation(description=" Donor donates Blood")
    public ResponseEntity donorDonateBlood(@Valid @RequestBody AddDonateBlood request) {
        //UserPrincipal currentUSer= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.donateBlood(request);
    }



}
