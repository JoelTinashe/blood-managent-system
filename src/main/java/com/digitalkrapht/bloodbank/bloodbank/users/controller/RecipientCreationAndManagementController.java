package com.digitalkrapht.bloodbank.bloodbank.users.controller;


import com.digitalkrapht.bloodbank.bloodbank.users.request.BloodRecipientRequest;
import com.digitalkrapht.bloodbank.bloodbank.users.request.UpdateBloodRecipientRequest;
import com.digitalkrapht.bloodbank.bloodbank.users.service.UserService;
import com.digitalkrapht.bloodbank.bloodbank.utils.constants.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class RecipientCreationAndManagementController {
    @Autowired
    UserService userService;



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
