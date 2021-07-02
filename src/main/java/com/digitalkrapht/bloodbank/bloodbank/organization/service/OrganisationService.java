package com.digitalkrapht.bloodbank.bloodbank.organization.service;

import com.digitalkrapht.bloodbank.bloodbank.organization.models.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.repositroy.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.request.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.response.*;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Permission;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Privilege;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.security.models.RoleName;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.PermissionRepository;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.PrivilegeRepository;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.RoleRepository;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PrivilegeResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.*;
import com.digitalkrapht.bloodbank.bloodbank.users.repository.*;
import com.digitalkrapht.bloodbank.bloodbank.users.request.*;
import com.digitalkrapht.bloodbank.bloodbank.users.response.*;
import com.digitalkrapht.bloodbank.bloodbank.users.service.UserService;
import com.digitalkrapht.bloodbank.bloodbank.utils.format.FormatUtility;
import com.digitalkrapht.bloodbank.bloodbank.utils.generators.StringGeneratorUtility;
import com.digitalkrapht.bloodbank.bloodbank.utils.responcse.GenericApiError;
import com.digitalkrapht.bloodbank.bloodbank.utils.responcse.GenericApiResponse;
import com.digitalkrapht.bloodbank.bloodbank.utils.responcse.PagedResponse;
import com.digitalkrapht.bloodbank.bloodbank.validation.ValidateUserProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.toList;

@Service
public class OrganisationService {
    @Autowired
    UserDonorRepository userDonorRepository;
    @Autowired
    OrganisationRepository organisationRepository;

    @Autowired
    UserBackOfficeAgentRepository userBackOfficeAgentRepository;

    @Autowired
    UserBackOfficeAdminRepository userBackOfficeAdminRepository;

    @Autowired
    UserRepository  userRepository;
    @Autowired
    FormatUtility formatUtility;
    @Autowired
    BloodGroupRepository bloodGrouprepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;
    @Autowired
    ValidateUserProperties validateUserProperties;
    @Autowired
    BloodRequestRepository bloodRequestRepository;
    @Autowired
    StringGeneratorUtility stringGeneratorUtility;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository repository ;
    @Autowired
    BloodGroupRepository bloodGroupRepository;
    @Autowired
    UserOrganisationAgentRepository organisationAgentRepository;
    @Autowired
    BloodRecipientRepositorry bloodRecipientRepositorry;
    @Autowired
    UserService userService;
    @Autowired
    BloodCollectionRepository bloodCollectionRepository;
    @Autowired
    StockDetailsRepository stockDetailsRepository;
    @Autowired
    StockDetailsLogRepository stockDetailsLogRepository;
    @Autowired
    DonorLogRepository donorLogRepository;



    //////////////////////////////////////OrganizationAgent////////////////////////////////////////////

    //////////  Create Organisation //////////////////////////////////////////////////////////

    public ResponseEntity createOrganisation(AddOrganisationRequest request) {

        ResponseEntity theResponse = validateUserProperties.isValidOrganisationRequest (request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }

        Organisation organization = new Organisation();

        organization.setEmail(request.getEmail());
        organization.setAddress(request.getAddress());
        organization.setOrganisationName(request.getOrganisationName());
        organization.setOrganisationType(request.getOrganisationType());
        organization.setContactPerson(request.getContactPerson());
        organization.setPhoneNumber(request.getPhoneNumber());
//        organizationAgent.setUserId(stringGeneratorUtility.fetchValidUserId(RoleName.ROLE_CUSTOMER_DONOR));

        Organisation saveOrganisation = organisationRepository.save(organization);
        return ResponseEntity.ok(new GenericApiResponse("Organisation agent  Created Successfully"));
    }
   /// /////////////////////////Update Organisation Agent///////////////////////////////////////////
    public ResponseEntity updateOrganisation(UpdateOrganisationRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateOrganisationRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        Organisation organisation = organisationRepository.findById(request.getId()).orElse(null);
        if(organisation==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Organisation ",110), HttpStatus.NOT_FOUND);
        }
        organisation.setEmail(request.getEmail());
        organisation.setOrganisationType(request.getOrganisationType());

        organisation.setContactPerson(request.getContactPerson());
        organisationRepository.save(organisation);
        return ResponseEntity.ok(new GenericApiResponse("Organisation  Updated Successfully"));
    }
    //get Organisation Agent by status
    public ResponseEntity getOrganisationByStatus(boolean status, int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC,"");
        Page<Organisation> organisation = organisationRepository.findByEnabled(status, pageable);
        List<OrganisationResponse> userSummaries = organisation.stream().map(this::mapOrganisationEntityToResponse).collect(toList());
        return ResponseEntity.ok(new PagedResponse<>(userSummaries, organisation.getNumber(),
                organisation.getSize(), organisation.getTotalElements(), organisation.getTotalPages(), organisation.isLast()));

    }
    // changing Organisation activate /deactivate Status
    public ResponseEntity changeOrganisationStatus(Long Id, boolean status) {
        Organisation organization= organisationRepository.findById(Id).orElse(null);
        if (organization == null) {
            return new ResponseEntity<>(new GenericApiError("Organisation Agent with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
        organization.setEnabled(status);
        organisationRepository.save(organization);
        return ResponseEntity.ok(new GenericApiResponse("Agent Status Successfully Updated"));

    }
    //get  active organisation
    public ResponseEntity getActiveOrganisation(){
        List<Organisation> agents = organisationRepository.findByEnabled(true);
        List<OrganisationResponse> userSummaries = agents.stream().map(this::mapOrganisationEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }
    //////////////////////////////////////Blood Creation////////////////////////////////////////////
    public ResponseEntity createBloodGroup(AddBloodGroupRequest request) {

        ResponseEntity theResponse = validateUserProperties.isValidBloodGroupRequest (request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }

        BloodGroup bloodGroup = new BloodGroup();

        bloodGroup.setBloodGroupName(request.getBloodGroupName());
        bloodGroup.setShortName(request.getShortName());
        bloodGrouprepository.save(bloodGroup);
        return ResponseEntity.ok(new GenericApiResponse("Blood group Created Successfully"));
    }
    /// /////////////////////////Update Organisation Agent///////////////////////////////////////////
    public ResponseEntity updateBloodGroup(UpdateBloodGroupRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateBloodGroupRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        BloodGroup bloodGroup = bloodGrouprepository.findById(request.getId()).orElse(null);
        if(bloodGroup==null){
            return new ResponseEntity<>(new GenericApiError("Could not load blood group ",110), HttpStatus.NOT_FOUND);
        }

        bloodGroup.setBloodGroupName(request.getBloodGroupName());
        bloodGroup.equals(request.getShortName());

        bloodGrouprepository.save(bloodGroup);
        return ResponseEntity.ok(new GenericApiResponse("Blood Group  Updated Successfully"));
    }
    // changing blood group activate /deactivate Status
    public ResponseEntity changeBloodGroupStatus(Integer id, boolean status) {
        BloodGroup  bloodGroup= bloodGrouprepository.findById(id).orElse(null);
        if (bloodGroup == null) {
            return new ResponseEntity<>(new GenericApiError("blood group with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
        bloodGroup.setEnabled(status);
        bloodGrouprepository.save(bloodGroup);
        return ResponseEntity.ok(new GenericApiResponse("blood group Status Successfully Updated"));

    }
    //get  active blood group
    public ResponseEntity getActiveBloodGroup(){
        List<BloodGroup> bloodGroups = bloodGrouprepository.findByEnabled(true);
        List<BloodGroupResponse> userSummaries = bloodGroups.stream().map(this::mapBloodGroupEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }

    ///////////////////////////////////Blood Request//////////////////////////////////////////////////////
    public ResponseEntity createBloodRequest(AddBloodRequest request) {

        ResponseEntity theResponse = validateUserProperties.isValidBloodRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        UserOrganizationAgent userOrganizationAgent =organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);
        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);


        BloodRequest bloodRequest = new BloodRequest();

        bloodRequest.setQuantity(request.getQuantity());
        bloodRequest.setBloodGroup(bloodGroup);
        bloodRequest.setBloodRecipient(bloodRecipient);
        bloodRequest.setUserOrganizationAgent(userOrganizationAgent);
         bloodRequestRepository.save(bloodRequest);


        return ResponseEntity.ok(new GenericApiResponse("Blood Request  Created Successfully"));
    }
    /// /////////////////////////Update Blood Request///////////////////////////////////////////
    public ResponseEntity updateBloodRequest(UpdateBloodRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateBloodRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }


        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        UserOrganizationAgent userOrganizationAgent =organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);
        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);
       BloodRequest bloodRequest = bloodRequestRepository.findById(request.getId()).orElse(null);
       if(bloodRequest==null){

           return new ResponseEntity<>(new GenericApiError("Could not load Blood Request ",110), HttpStatus.NOT_FOUND);

       }
       BloodRequest blood = new BloodRequest();
       blood.setQuantity(request.getQuantity());
       blood.setBloodRecipient(bloodRecipient);
       blood.setBloodGroup(bloodGroup);
       blood.setUserOrganizationAgent(userOrganizationAgent);

       bloodRequestRepository.save(blood);
        return ResponseEntity.ok(new GenericApiResponse("Blood request/ Updated Successfully"));
    }
    //  activate /deactivate of Blood Request
    public ResponseEntity changeBloodRequestStatus(Long Id, boolean status) {
        BloodRequest bloodRequest = bloodRequestRepository.findById(Id).orElse(null);
        if(bloodRequest==null){
            return new ResponseEntity<>(new GenericApiError("Blood Request with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);


        }

        bloodRequest.setEnabled(status);
        bloodRequestRepository.save(bloodRequest);
        return ResponseEntity.ok(new GenericApiResponse("Blood Request Status Successfully Updated"));

    }
    //get  active  Blood Request
    public ResponseEntity getActiveBloodRequest(){
        List<BloodRequest> bloodRequests = bloodRequestRepository.findByEnabled(true);
        List<BloodRequestResponse> userSummaries = bloodRequests.stream().map(this::mapBloodRequestEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }
    // //Get all blood Request available
    public ResponseEntity getAllBloodRequests(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<BloodRequest> failures = bloodRequestRepository.findAll(pageable);

        List<BloodRequestResponse> ticketFailureResponses = failures.stream().map(this::mapBloodRequestEntityToResponse).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }

////////////////////////////////Create Blood Collection///////////////////////////////////////////////////////////
    public ResponseEntity createBloodCollection(AddBloodCollectionRequest request) {

    ResponseEntity theResponse = validateUserProperties.isValidBloodCollection(request);
    if (theResponse.getStatusCode().value() != 200) {
        return theResponse;
    }
    BloodCollection bloodCollection = new BloodCollection();
    BloodRequest bloodRequest = bloodRequestRepository.findById(request.getBloodRequestId()).orElse(null);
    BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);
    UserBackOfficeAdmin backOfficeAdmin = userBackOfficeAdminRepository.findById(request.getBackOfficeAdminId()).orElse(null);
    UserOrganizationAgent userOrganizationAgent = organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);
        DonorLogsRequest request1 = new DonorLogsRequest();

    bloodCollection.setBloodRecipient(bloodRecipient);
    bloodCollection.setBloodRequest(bloodRequest);
    bloodCollection.setBackOfficeAdmin(backOfficeAdmin);
    bloodCollection.setOrganizationAgent(userOrganizationAgent);
        bloodCollectionRepository.save(bloodCollection);
//        DonorLogsRequest logs = new DonorLogsRequest();
//
//
//        request1.setBloodDonorId(logs.getBloodDonorId());
//        request1.setBackAgentId(logs.getBackAgentId());
//        request1.setDonationDate(logs.getDonationDate());
//



    return ResponseEntity.ok(new GenericApiResponse("Blood Collection Created Successfully"));
}
    public ResponseEntity updateBloodCollect(UpdateBloodCollectionRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateBloodCollection(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;

        }
        BloodCollection bloodCollection = bloodCollectionRepository.findById(request.getId()).orElse(null);
        if(bloodCollection==null){
            return new ResponseEntity<>(new GenericApiError("Could load Collection Id",110), HttpStatus.EXPECTATION_FAILED);
        }
            BloodRequest bloodRequest = bloodRequestRepository.findById(request.getBloodRequestId()).orElse(null);
            BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(request.getBloodRecipientId()).orElse(null);
            UserBackOfficeAdmin backOfficeAdmin = userBackOfficeAdminRepository.findById(request.getBackOfficeAdminId()).orElse(null);
            UserOrganizationAgent userOrganizationAgent = organisationAgentRepository.findById(request.getOrganisationAgentId()).orElse(null);

            BloodCollection collection = new  BloodCollection();
            collection.setOrganizationAgent(userOrganizationAgent);
            collection.setBloodRequest(bloodRequest);
            collection.setBloodRecipient(bloodRecipient);
            collection.setBackOfficeAdmin(backOfficeAdmin);
        bloodCollectionRepository.save(collection);
        return ResponseEntity.ok(new GenericApiResponse("Blood Collection request Updated Successfully"));
    }
    public ResponseEntity changeBloodCollectionStatus(Integer Id, boolean status) {
        BloodCollection bloodCollection= bloodCollectionRepository.findById(Id).orElse(null);
        if(bloodCollection==null){
            return new ResponseEntity<>(new GenericApiError("Blood Collection with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);


        }

        bloodCollection.setEnabled(status);
        bloodCollectionRepository.save(bloodCollection);
        return ResponseEntity.ok(new GenericApiResponse("Blood Collection Status Successfully Updated"));

    }
    //get  active  collection
    public ResponseEntity getActiveBloodCollection(){
        List<BloodCollection> bloodCollection = bloodCollectionRepository.findByEnabled(true);
        List<BloodCollectionResponse> userSummaries = bloodCollection.stream().map(this::mapBloodCollectionEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }
    public ResponseEntity getAllBloodCollections(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<BloodCollection> failures = bloodCollectionRepository.findAll(pageable);

        List<BloodCollectionResponse> ticketFailureResponses = failures.stream().map(this::mapBloodCollectionEntityToResponse).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }


////////////////////////////////////////Stock Details//////////////////////////////////////////////////////

    public ResponseEntity createStockDetails(AddStockRequest request) {

        ResponseEntity theResponse = validateUserProperties.isValidStockDetails(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        StockDetails stockDetails = new StockDetails();


        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        UserBackOfficeAgent backOfficeAgent= userBackOfficeAgentRepository.findById(request.getBackAgentId()).orElse(null);
        UserDonor userDonor = userDonorRepository.findById(request.getBloodDonorId()).orElse(null);

        stockDetails.setQuantity(request.getQuantity());
        stockDetails.setUnit(request.getUnit());
        stockDetails.setStockAdjustmentNotes(request.getStockAdjustmentNotes());
        stockDetails.setStockAdjustmentType(request.getStockAdjustmentType());
        stockDetails.setBloodGroup(bloodGroup);
        stockDetails.setBackOfficeAgent(backOfficeAgent);
        stockDetails.setDonors(userDonor);
             StockDetails stock = stockDetailsRepository.save(stockDetails);
             stockDetailsLogRepository.save(stock);
        return ResponseEntity.ok(new GenericApiResponse("Blood Stock Created Successfully"));
    }
    public ResponseEntity updateStockDetails(UpdateStockDetailsRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateStockDetails(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;

        }
        StockDetails stockDetail = stockDetailsRepository.findById(request.getId()).orElse(null);
        if(stockDetail==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Stock Id",110), HttpStatus.EXPECTATION_FAILED);

        }

        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        UserBackOfficeAgent backOfficeAgent= userBackOfficeAgentRepository.findById(request.getBackAgentId()).orElse(null);
        UserDonor userDonor = userDonorRepository.findById(request.getBloodDonorId()).orElse(null);

        StockDetails stockDetails = new StockDetails();
        stockDetails.setDonors(userDonor);
        stockDetails.setBloodGroup(bloodGroup);
        stockDetails.setBackOfficeAgent(backOfficeAgent);
        stockDetails.setStockAdjustmentType(request.getStockAdjustmentType());
        stockDetails.setStockAdjustmentNotes(request.getStockAdjustmentNotes());
        stockDetails.setUnit(request.getUnit());
        stockDetails.setQuantity(request.getQuantity());
         StockDetails stock=  stockDetailsRepository.save(stockDetails);
         stockDetailsLogRepository.save(stock);
        return ResponseEntity.ok(new GenericApiResponse("Stock details request Updated Successfully"));
    }
    public ResponseEntity getAllStockDetails(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<StockDetails> failures = stockDetailsRepository.findAll(pageable);

        List<StockDetailsResponse> ticketFailureResponses = failures.stream().map(this::mapBloodStockDetailsEntityToResponse).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }

    /////////////////////////// Inside Methods////////////////////////////////////////////////////
    //mapping Organisation Response to the  Entity
    public StockDetailsResponse mapBloodStockDetailsEntityToResponse(StockDetails stockDetails) {
        StockDetailsResponse stockDetailsResponse = new StockDetailsResponse();
        if(stockDetails.getBloodGroup()!=null){
            stockDetailsResponse.setBloodGroup(this.mapBloodGroupEntityToResponse(stockDetails.getBloodGroup()));
        }
        if(stockDetails.getBackOfficeAgent()!=null){
            stockDetailsResponse.setBackOfficeAgent(userService.mapBackOfficeAgentEntityToResponse(stockDetails.getBackOfficeAgent()));
        }
        if(stockDetails.getDonors()!=null){
            stockDetailsResponse.setUserDonor(userService.mapUserDonorResponseEntityToResponse(stockDetails.getDonors()));
        }

       stockDetailsResponse.setId(stockDetails.getId());
        stockDetailsResponse.setQuantity(stockDetails.getQuantity());
        stockDetailsResponse.setUnit(stockDetails.getUnit());
        stockDetailsResponse.setStockAdjustmentNotes(stockDetails.getStockAdjustmentNotes());
        stockDetailsResponse.setStockAdjustmentType(stockDetails.getStockAdjustmentType());

        return stockDetailsResponse;
    }
    public BloodRequestResponse mapBloodRequestEntityToResponse(BloodRequest bloodRequest) {
        BloodRequestResponse requestResponse = new BloodRequestResponse();
        if(bloodRequest.getBloodGroup()!=null){
            requestResponse.setBloodGroup(this.mapBloodGroupEntityToResponse(bloodRequest.getBloodGroup()));
        }
        if(bloodRequest.getBloodRecipient()!=null){
            requestResponse.setBloodRecipient(userService.mapBloodRecipientResponseEntity(bloodRequest.getBloodRecipient()));
        }
        if(bloodRequest.getUserOrganizationAgent()!=null){
            requestResponse.setUserOrganizationAgent(userService.mapUserOrganisationAgentEntityToResponse(bloodRequest.getUserOrganizationAgent()));
        }
        requestResponse.setId(bloodRequest.getId());
        requestResponse.setQuantity(bloodRequest.getQuantity());
        requestResponse.setActive(bloodRequest.getEnabled());
        requestResponse.setBLoodStatus(bloodRequest.getBLoodStatus());
        return requestResponse;
    }
    public OrganisationResponse mapOrganisationEntityToResponse(Organisation organisation) {

        OrganisationResponse response = new OrganisationResponse();
        response.setEmail(organisation.getEmail());
        response.setId(organisation.getId());
        response.setOrganisationName(organisation.getOrganisationName());
        response.setAddress(organisation.getAddress());
        response.setOrganisationType(organisation.getOrganisationType());
        response.setContactPerson(organisation.getContactPerson());
        response.setActive(organisation.getEnabled());
        return response;
    }
    public BloodGroupResponse mapBloodGroupEntityToResponse(BloodGroup bloodGroup) {
        BloodGroupResponse response = new BloodGroupResponse();
        response.setId(bloodGroup.getId());
        response.setBloodGroupName(bloodGroup.getBloodGroupName());
        response.setShortName(bloodGroup.getShortName());
        response.setActive(bloodGroup.isEnabled());
        return response;
    }
    public BloodCollectionResponse mapBloodCollectionEntityToResponse(BloodCollection bloodCollection) {
        BloodCollectionResponse response = new BloodCollectionResponse();
     if(bloodCollection.getBloodRecipient()!=null){

         response.setBloodRecipient(userService.mapBloodRecipientResponseEntity(bloodCollection.getBloodRecipient()));
     }
     if(bloodCollection.getBloodRequest()!=null){

         response.setBloodRequest(this.mapBloodRequestEntityToResponse(bloodCollection.getBloodRequest()));
     }
     if(bloodCollection.getOrganizationAgent()!=null){

         response.setUserOrganisationAgent(userService.mapUserOrganisationAgentEntityToResponse(bloodCollection.getOrganizationAgent()));

     }
     if(bloodCollection.getBackOfficeAdmin()!=null){

         response.setUserBackOfficeAdmin(userService.mapBackOfficeAdminEntityToResponse(bloodCollection.getBackOfficeAdmin()));

     }
      response.setQuantity(bloodCollection.getQuantity());
     response.setId(bloodCollection.getId());
     response.setActive(bloodCollection.isEnabled());

        return response;
    }


}
