package com.digitalkrapht.bloodbank.bloodbank.validation;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.enums.BloodStatus;
import com.digitalkrapht.bloodbank.bloodbank.organization.repositroy.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.*;
import com.digitalkrapht.bloodbank.bloodbank.users.models.*;
import com.digitalkrapht.bloodbank.bloodbank.users.repository.*;
import com.digitalkrapht.bloodbank.bloodbank.users.request.*;
import com.digitalkrapht.bloodbank.bloodbank.utils.format.FormatUtility;
import com.digitalkrapht.bloodbank.bloodbank.utils.responcse.GenericApiError;
import com.digitalkrapht.bloodbank.bloodbank.utils.responcse.GenericApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ValidateUserProperties {

    @Autowired
    UserBackOfficeAdminRepository userBackOfficeAdminRepository;
    @Autowired
    OrganisationRepository organisationRepository;
    @Autowired
    UserOrganisationAgentRepository organisationAgentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BloodRequestRepository bloodRequestRepository;
    @Autowired
    UserDonorRepository userDonorRepository;
    @Autowired
    FormatUtility formatUtility;
    @Autowired
    BloodRecipientRepositorry bloodRecipientRepositorry;
    @Autowired
    BloodGroupRepository bloodGroupRepository;
    @Autowired
    UserBackOfficeAgentRepository userBackOfficeAgentRepository;
    @Autowired
    BloodCollectionRepository bloodCollectionRepository;
    @Autowired
    StockDetailsRepository stockDetailsRepository;

    public ResponseEntity isValidAddBackOfficeAdminRequest(AddUserBackOfficeAdminRequest request){
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User admin = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(admin!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateBackOfficeAdminRequest(UpdateUserBackOfficeAdminRequest request){
        UserBackOfficeAdmin userBackendAgent = userBackOfficeAdminRepository.findById(request.getUserId()).orElse(null);
        if(userBackendAgent==null){
            return new ResponseEntity<>(new GenericApiError("Could not Load User",110), HttpStatus.NOT_FOUND);
        }
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User admin = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(admin!=null && !admin.getUserId().equals(userBackendAgent.getUserId())){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    // Validating BAck office  Agent request and updating
    public ResponseEntity isValidAddBackOfficeAgentRequest(AddUserBackOfficeAgentRequest request){
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }





        if(request.getGender()==null){

            return new ResponseEntity(new GenericApiError("Gender can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(request.getMobileNumber()==null || request.getMobileNumber().isEmpty()){

            return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateBackOfficeAgentRequest(UpdateUserBackOfficeAgentRequest request){
        UserBackOfficeAgent userBackendAgent = userBackOfficeAgentRepository.findById(request.getUserId()).orElse(null);
        if(userBackendAgent==null){
            return new ResponseEntity<>(new GenericApiError("Could not Load User",110), HttpStatus.NOT_FOUND);
        }
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }



        if(request.getGender()==null){

            return new ResponseEntity(new GenericApiError("Gender can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(request.getMobileNumber()< 0){

            return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User admin = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(admin!=null && !admin.getUserId().equals(userBackendAgent.getUserId())){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    // Validating back Customer donor request and Updating
    public ResponseEntity isValidCustomerDonorRequest(AddUserDonorRequest request){

        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        if(bloodGroup==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Blood Group Id provided",110), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }


        if(request.getNationalID()==null || request.getNationalID().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("National ID cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getAge() <  5 || request.getAge() > 90 ){
            return new ResponseEntity<>(new GenericApiError("Donor must be over 5 or less than 90 ",105), HttpStatus.EXPECTATION_FAILED);
        }


        if(request.getGender()==null ){

            return new ResponseEntity(new GenericApiError("Gender can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(request.getMobileNumber().length() < 6){

            return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateCustomerDonorRequest(UpdateUserDonorRequest request){
        UserDonor userDonor = userDonorRepository.findById(request.getUserId()).orElse(null);
        if(userDonor==null){
            return new ResponseEntity<>(new GenericApiError("Could not Load Donor with the provided Id",110), HttpStatus.NOT_FOUND);
        }
     if(request.getFirstName()==null || request.getFirstName().isEmpty()){
         return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
     }
     if(request.getLastName()==null || request.getLastName().isEmpty()){
         return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
     }
     if(request.getEmail()==null || request.getEmail().isEmpty()){
         return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
     }



     if(request.getAge() <  5 || request.getAge() > 90 ){
         return new ResponseEntity<>(new GenericApiError("Donor must be over 5 or less than 90 ",105), HttpStatus.EXPECTATION_FAILED);
     }


     if(request.getGender()==null ){

         return new ResponseEntity(new GenericApiError("Gender can not be empty",105),HttpStatus.BAD_REQUEST);
     }


     if(request.getMobileNumber().length() < 6){

         return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
     }


     if(!formatUtility.isValidEmail(request.getEmail())){
         return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
     }
     User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
     if(agent!=null){
         return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
     }
     return ResponseEntity.ok(true);
 }
   // Validating Organisation agent request and updating
    public ResponseEntity isValidOrganisationAgentRequest(AddUserOrganizationAgentRequest request){
        Organisation organisation =  organisationRepository.findById(request.getOrganisationId()).orElse(null);
        if(organisation==null){
            return new ResponseEntity<>(new GenericApiError("Could not load the organisation with the provided Id",110), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getAddress()==null || request.getAddress().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Address cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getDesignation()==null || request.getDesignation().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Designation cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getGender()==null ){

            return new ResponseEntity(new GenericApiError("Gender can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(request.getMobileNumber().length() < 6){

            return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
        }

        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateOrganisationAgentRequest(UpdateUserOrganizationAgentRequest request){
        UserOrganizationAgent organizationAgent = organisationAgentRepository.findById(request.getUserId()).orElse(null);
        if(organizationAgent==null){

            return new ResponseEntity<>(new GenericApiError("Could not Load organisation Agent with the provided Id",110), HttpStatus.NOT_FOUND);
        }
        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getAddress()==null || request.getAddress().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Address cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getDesignation()==null || request.getDesignation().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Designation cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getGender()==null ){

            return new ResponseEntity(new GenericApiError("Gender can not be empty",105),HttpStatus.BAD_REQUEST);
        }


        if(request.getMobileNumber().length() < 6){

            return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
        }

        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    // Validating Organisation  request and updating
    public ResponseEntity isValidOrganisationRequest(AddOrganisationRequest request){
        if(request.getOrganisationName()==null|| request.getOrganisationName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Organisation Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getPhoneNumber()==null || request.getPhoneNumber().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Phone number cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getAddress()==null || request.getAddress().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Address cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getContactPerson()==null || request.getContactPerson().isEmpty() ){

            return new ResponseEntity(new GenericApiError("Contact person can not be empty",105),HttpStatus.BAD_REQUEST);
        }

        if(request.getOrganisationType()==null ){

            return new ResponseEntity(new GenericApiError("Organisation can not be empty",105),HttpStatus.BAD_REQUEST);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty() ){

            return new ResponseEntity(new GenericApiError("Email can not be empty",105),HttpStatus.BAD_REQUEST);
        }

        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateOrganisationRequest(UpdateOrganisationRequest request){
        Organisation organisation = organisationRepository.findById(request.getId()).orElse(null);
        if(organisation==null){
            return new ResponseEntity<>(new GenericApiError("Could not Load Organisation with the provided Id",110), HttpStatus.NOT_FOUND);
        }

        if(request.getOrganisationName()==null|| request.getOrganisationName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Organisation Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getPhoneNumber()==null || request.getPhoneNumber().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Phone number cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getAddress()==null || request.getAddress().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Address cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getContactPerson()==null || request.getContactPerson().isEmpty() ){

            return new ResponseEntity(new GenericApiError("Contact person can not be empty",105),HttpStatus.BAD_REQUEST);
        }

        if(request.getOrganisationType()==null ){

            return new ResponseEntity(new GenericApiError("Organisation can not be empty",105),HttpStatus.BAD_REQUEST);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty() ){

            return new ResponseEntity(new GenericApiError("Email can not be empty",105),HttpStatus.BAD_REQUEST);
        }

        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);




    }
    // Validating Blood  request and updating
    public ResponseEntity isValidBloodGroupRequest(AddBloodGroupRequest request){
        if(request.getBloodGroupName()==null|| request.getBloodGroupName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Blood group cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getShortName()==null || request.getShortName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Short name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateBloodGroupRequest(UpdateBloodGroupRequest request){
        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodId()).orElse(null);

        if(bloodGroup==null){

            return new ResponseEntity<>(new GenericApiError("Could not Load Blood group",110), HttpStatus.NOT_FOUND);

        }

        if(request.getBloodGroupName()==null|| request.getBloodGroupName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Blood group cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getShortName()==null || request.getShortName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Short name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    // Validating Blood Recipient  request and updating
    public ResponseEntity isValidBloodRecipientRequest(BloodRecipientRequest request){
        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        if(bloodGroup==null){
            return new ResponseEntity<>(new GenericApiError("Could not Blood group with the provided Id",110), HttpStatus.NOT_FOUND);
        }

        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getAddress()==null || request.getAddress().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Address cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getGender()==null ){

            return new ResponseEntity(new GenericApiError("Gender can not be empty",105),HttpStatus.BAD_REQUEST);
        }
        if(request.getIdNumber()==null || request.getIdNumber().isEmpty()){

            return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
        }
        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateBloodRecipientRequest(UpdateBloodRecipientRequest request){

        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getUserId()).orElse(null);
        if(bloodRecipient==null){
            return new ResponseEntity<>(new GenericApiError("Could not Load Recipient with the provided Id",110), HttpStatus.NOT_FOUND);

        }

        if(request.getFirstName()==null || request.getFirstName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("First Name cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getLastName()==null || request.getLastName().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Surname cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getEmail()==null || request.getEmail().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Email cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getAddress()==null || request.getAddress().isEmpty()){
            return new ResponseEntity<>(new GenericApiError("Address cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getPhoneNumber()==null || request.getPhoneNumber().isEmpty()){

            return new ResponseEntity(new GenericApiError("Phone number can not be empty",105),HttpStatus.BAD_REQUEST);
        }

        if(!formatUtility.isValidEmail(request.getEmail())){
            return new ResponseEntity<>(new GenericApiError("Email is not valid",106), HttpStatus.EXPECTATION_FAILED);
        }
        User agent = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(agent!=null){
            return new ResponseEntity<>(new GenericApiError("Another Account with Same Email Already Exists",104), HttpStatus.EXPECTATION_FAILED);
        }
        return ResponseEntity.ok(true);
    }
    /////////////////////////////////Validating Blood Request and Updating/////////////////////
 public ResponseEntity isValidBloodRequest(AddBloodRequest request){

//       StockDetails stockDetails =stockDetailsRepository.findByQuantity(request.getQuantity()).orElse(null);
//        if(stockDetails==null){
//
//            return new ResponseEntity<>(new GenericApiError("Could not load Stock Id Id",110), HttpStatus.EXPECTATION_FAILED);
//        }

    // StockDetails stockDetails= stockDetailsRepository.findById(request.getBloodGroupId()).orElse(null);
     BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        if(bloodGroup==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Blood group Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        UserOrganizationAgent userOrganizationAgent =organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);

        if(userOrganizationAgent==null){
            return new ResponseEntity<>(new GenericApiError("could not load Agent with the provided Id",417), HttpStatus.EXPECTATION_FAILED);
        }
        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);
        if(bloodRecipient==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Recipient with the provided Id",110), HttpStatus.EXPECTATION_FAILED);
        }

     if(bloodGroup.getBloodId()!= bloodRecipient.getBloodGroup().getBloodId()){

         return new ResponseEntity<>(new GenericApiError("Could not load Recipient Blood Group Id ",405), HttpStatus.METHOD_NOT_ALLOWED);



     }

        if(request.getQuantity()<0){
            return new ResponseEntity<>(new GenericApiError("Quantity cannot be Zero",110), HttpStatus.EXPECTATION_FAILED);
        }



        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateBloodRequest(UpdateBloodRequest request){
        BloodRequest bloodRequest = bloodRequestRepository.findById(request.getId()).orElse(null);
        if(bloodRequest==null){
            return new ResponseEntity<>(new GenericApiError("Could not the provide Blood Request Id",110), HttpStatus.EXPECTATION_FAILED);
        }

        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        if(bloodGroup==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Blood group Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        UserOrganizationAgent userOrganizationAgent =organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);

        if(userOrganizationAgent==null){
            return new ResponseEntity<>(new GenericApiError("could not load Agent with the provided Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);
        if(bloodRecipient==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Recipient with the provided Id",110), HttpStatus.EXPECTATION_FAILED);
        }


        if(request.getQuantity()<0){
            return new ResponseEntity<>(new GenericApiError("Quantity cannot be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        return ResponseEntity.ok(true);
    }

    //////////////////validating Blood Collection/////////////////////////////////////////////////
    public ResponseEntity isValidBloodCollection(AddBloodCollectionRequest request){

        BloodRequest bloodRequest = bloodRequestRepository.findById(request.getBloodRequestId()).orElse(null);

        if(bloodRequest.getBloodStatus().equals(BloodStatus.PENDING)){

            return new ResponseEntity<>(new GenericApiError("can not collect blood while request is still pending", 405), HttpStatus.EXPECTATION_FAILED);
        }

        if(bloodRequest.getBloodStatus().equals(BloodStatus.REJECTED)){

            return new ResponseEntity<>(new GenericApiError("can not collect blood that was rejected ", 405), HttpStatus.EXPECTATION_FAILED);
        }

        UserOrganizationAgent userOrganizationAgent = organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);
        if(userOrganizationAgent==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Agent Id",110), HttpStatus.EXPECTATION_FAILED);
        }

        UserBackOfficeAdmin backOfficeAdmin = userBackOfficeAdminRepository.findById(request.getBackOfficeAdminId()).orElse(null);
        if(backOfficeAdmin==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Admin Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);
        if(bloodRecipient==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Blood Recipient Id",110), HttpStatus.EXPECTATION_FAILED);
        }

        if(bloodRequest==null){
            return new ResponseEntity<>(new GenericApiError("could not load Blood request with the provided Id",110), HttpStatus.EXPECTATION_FAILED);

        }


        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateBloodCollection(UpdateBloodCollectionRequest request){
        BloodCollection bloodCollection = bloodCollectionRepository.findById(request.getId()).orElse(null);
        if(bloodCollection==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Collect Id",110), HttpStatus.EXPECTATION_FAILED);

        }

        UserOrganizationAgent userOrganizationAgent = organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);
        if(userOrganizationAgent==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Agent Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        UserBackOfficeAdmin backOfficeAdmin = userBackOfficeAdminRepository.findById(request.getBackOfficeAdminId()).orElse(null);
        if(backOfficeAdmin==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Admin Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);
        if(bloodRecipient==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Blood Recipient Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        BloodRequest bloodRequest = bloodRequestRepository.findById(request.getBloodRequestId()).orElse(null);

        if(bloodRequest==null){
            return new ResponseEntity<>(new GenericApiError("could not load Blood request with the provided Id",110), HttpStatus.EXPECTATION_FAILED);

        }





        return ResponseEntity.ok(true);
    }

    //////////////////validating Stock details/////////////////////////////////////////////////
    public ResponseEntity isValidStockDetails(AddStockRequest request){

        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        if(bloodGroup==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Blood  Group Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        UserBackOfficeAgent backOfficeAgent  = userBackOfficeAgentRepository.findById(request.getBackAgentId()).orElse(null);
        if(backOfficeAgent==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Agent Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        UserDonor userDonor  = userDonorRepository.findById(request.getBloodDonorId()).orElse(null);
        if(userDonor==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Blood donor Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getQuantity()<0 ){
            return new ResponseEntity<>(new GenericApiError("Quantity can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getUnit()==null || request.getUnit().isEmpty()){

            return new ResponseEntity<>(new GenericApiError("Blood unity can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getStockAdjustmentType()==null ){

            return new ResponseEntity<>(new GenericApiError("Stock Adjustment Type can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getStockAdjustmentNotes()==null || request.getStockAdjustmentNotes().isEmpty()){

            return new ResponseEntity<>(new GenericApiError("Stock Adjustment Notes can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }


        return ResponseEntity.ok(true);
    }
    public ResponseEntity isValidUpdateStockDetails(UpdateStockDetailsRequest request){
        StockDetails stockDetail = stockDetailsRepository.findById(request.getId()).orElse(null);
        if(stockDetail==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Stock Id",110), HttpStatus.EXPECTATION_FAILED);

        }


        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        if(bloodGroup==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Blood  Group Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        UserBackOfficeAgent backOfficeAgent  = userBackOfficeAgentRepository.findById(request.getBackAgentId()).orElse(null);
        if(backOfficeAgent==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Agent Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        UserDonor userDonor  = userDonorRepository.findById(request.getBloodDonorId()).orElse(null);
        if(userDonor==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Blood donor Id",110), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getQuantity()<0){
            return new ResponseEntity<>(new GenericApiError("Quantity can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getUnit()==null || request.getUnit().isEmpty()){

            return new ResponseEntity<>(new GenericApiError("Blood unity can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getStockAdjustmentType()==null ){

            return new ResponseEntity<>(new GenericApiError("Stock Adjustment Type can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        if(request.getStockAdjustmentNotes()==null || request.getStockAdjustmentNotes().isEmpty()){

            return new ResponseEntity<>(new GenericApiError("Stock Adjustment Notes can not be empty",105), HttpStatus.EXPECTATION_FAILED);
        }




        return ResponseEntity.ok(true);
    }

    public ResponseEntity isValidDonateBlood(AddDonateBlood request){

        UserDonor userDonor = userDonorRepository.findById(request.getBloodDonorId()).orElse(null);
        if(userDonor==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Blood Donor Id ",110), HttpStatus.EXPECTATION_FAILED);
        }
        if(request.getDonatedQuantity()<0 ){
            return new ResponseEntity<>(new GenericApiError("Blood Quantity can be empty",105), HttpStatus.EXPECTATION_FAILED);
        }

        Organisation organisation = organisationRepository.findById(request.getOrganisationId()).orElse(null);
        if(organisation==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Organisation Id ",110), HttpStatus.EXPECTATION_FAILED);


        }

        return ResponseEntity.ok(true);

    }





}










