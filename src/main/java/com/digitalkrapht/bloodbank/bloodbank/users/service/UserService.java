package com.digitalkrapht.bloodbank.bloodbank.users.service;

import com.digitalkrapht.bloodbank.bloodbank.nofications.email.executor.EmailExecutor;
import com.digitalkrapht.bloodbank.bloodbank.nofications.email.request.Mail;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.BloodGroup;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.Organisation;
import com.digitalkrapht.bloodbank.bloodbank.organization.models.StockDetails;
import com.digitalkrapht.bloodbank.bloodbank.organization.repositroy.*;
import com.digitalkrapht.bloodbank.bloodbank.organization.service.OrganisationService;
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

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    @Autowired
    UserDonorRepository userDonorRepository;

    @Autowired
    UserBackOfficeAgentRepository userBackOfficeAgentRepository;

    @Autowired
    UserBackOfficeAdminRepository userBackOfficeAdminRepository;

    @Autowired
    UserRepository  userRepository;
    @Autowired
    UserOrganisationAgentRepository organisationAgentRepository;

    @Autowired
    FormatUtility formatUtility;
    @Autowired
    RoleRepository  repository;
    @Autowired
    OrganisationRepository organisationRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;
    @Autowired
    ValidateUserProperties validateUserProperties;

    @Autowired
    StringGeneratorUtility stringGeneratorUtility;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    BloodGroupRepository bloodGroupRepository;

    @Autowired
    OrganisationService organisationService;

    @Autowired
    DonateBloodRepository donateBloodRepository;
    @Autowired
    BloodRecipientRepositorry bloodRecipientRepositorry;
    @Autowired
    DonorLogRepository donorLogRepository;
    @Autowired
    EmailExecutor emailExecutor;
    @Autowired
    StockDetailsRepository stockDetailsRepository;




    //////////////////////Back Office Admins////////////////////////////////////////////////////////
    public ResponseEntity getBackOfficeAdminsByStatus(boolean status, int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<UserBackOfficeAdmin> users = userBackOfficeAdminRepository.findByEnabled(status, pageable);
        List<UserBackOfficeAdminResponse> userSummaries = users.stream().map(this::mapBackOfficeAdminEntityToResponse).collect(toList());
        return ResponseEntity.ok(new PagedResponse<>(userSummaries, users.getNumber(),
                users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast()));
    }
    public ResponseEntity getActiveBackOfficeAdmins() {
        List<UserBackOfficeAdmin> admins = userBackOfficeAdminRepository.findByEnabled(true);
        List<UserBackOfficeAdminResponse> userSummaries = admins.stream().map(this::mapBackOfficeAdminEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }
    public ResponseEntity changeBackOfficeAdminStatus(String UserId, boolean status) {
        UserBackOfficeAdmin userShopAdmin = userBackOfficeAdminRepository.findById(UserId).orElse(null);
        if (userShopAdmin == null) {
            return new ResponseEntity<>(new GenericApiError("Office Admin with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
        userShopAdmin.setEnabled(status);
        userBackOfficeAdminRepository.save(userShopAdmin);
        return ResponseEntity.ok(new GenericApiResponse("Office Admin Status Successfully Updated"));

    }
    public ResponseEntity getAllBackOfficeAdmins(int page, int size,String userId) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<UserBackOfficeAdmin> failures = userBackOfficeAdminRepository.findAll(pageable);

        List<UserBackOfficeAdminResponse> ticketFailureResponses = failures.stream().map(this::mapBackOfficeAdminEntityToResponse).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }
    public ResponseEntity getBackAdminById(String userId,String user) {

        UserBackOfficeAdmin admin = userBackOfficeAdminRepository.findById(userId).orElse(null);
        if (admin == null) {
            return new ResponseEntity<>(new GenericApiError("Could Not Find Office Admin", 110), HttpStatus.NOT_FOUND);
        }
        UserBackOfficeAdminResponse response = this.mapBackOfficeAdminEntityToResponse(admin);

        return ResponseEntity.ok(response);

    }
    // create Back office admins
    public ResponseEntity addBackOfficeAdmin(AddUserBackOfficeAdminRequest request,String userId) {

        ResponseEntity theResponse = validateUserProperties.isValidAddBackOfficeAdminRequest (request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        Role role = repository.findByName(RoleName.ROLE_BACK_OFFICE_ADMIN).orElse(null);
        if (role == null) {
            return new ResponseEntity<>(new GenericApiError("could not load users Role", 110), HttpStatus.NOT_FOUND);
        }
        String pin = RandomStringUtils.random(4, false, true);
        UserBackOfficeAdmin admin = new UserBackOfficeAdmin();
        admin.setEmail(request.getEmail());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setUserId(stringGeneratorUtility.fetchValidUserId(RoleName.ROLE_BACK_OFFICE_ADMIN));
        admin.setResetPin(true);
        admin.setPassword(passwordEncoder.encode(pin));
        admin.setRoles(Collections.singletonList(role));
        admin.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        admin.setUsername(request.getEmail());
        String emailMessage = "You Have Successfully received Your Password  Your new Pin Is "+ pin;
        this.processGetPasswordNotifications(admin.getEmail(),emailMessage);
        UserBackOfficeAdmin savedAdmin = userBackOfficeAdminRepository.save(admin);
        //////////////////////assign Permissions////////////////////////
        assignPermissionsByRoleToUser(RoleName.ROLE_BACK_OFFICE_ADMIN,savedAdmin);
        return ResponseEntity.ok(new GenericApiResponse("Blood Backend Admin Created Successfully" +
                "Please check your Email for your password"
                ));

    }
    // updating Back office Admin
    public ResponseEntity updateBackOfficeAdmin(UpdateUserBackOfficeAdminRequest request,String userId){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateBackOfficeAdminRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        UserBackOfficeAdmin admin = userBackOfficeAdminRepository.findById(request.getUserId()).orElse(null);
        if(admin==null){
            return new ResponseEntity<>(new GenericApiError("Could Not Load Shop Admin Profile",110), HttpStatus.NOT_FOUND);
        }
        admin.setEmail(request.getEmail());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        admin.setUsername(request.getEmail());
        admin.setGender(request.getGender());
        userBackOfficeAdminRepository.save(admin);
        return ResponseEntity.ok(new GenericApiResponse("Back Office Admin Updated Successfully"));
    }



//////////////////////////////////////// Back Office agents////////////////////////////////////////////////////////////

    //get Back agents by status
    public ResponseEntity getBackOfficeAgentsByStatus(boolean status, int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<UserBackOfficeAgent> agentUser = userBackOfficeAgentRepository.findByEnabled(status, pageable);
        List<UserBackOfficeAgentResponse> userSummaries = agentUser.stream().map(this::mapBackOfficeAgentEntityToResponse).collect(toList());
        return ResponseEntity.ok(new PagedResponse<>(userSummaries, agentUser.getNumber(),
                agentUser.getSize(), agentUser.getTotalElements(), agentUser.getTotalPages(), agentUser.isLast()));
    }
    // adding BAck office Agent
    public ResponseEntity createBackOfficeAgent(AddUserBackOfficeAgentRequest request) {

        ResponseEntity theResponse = validateUserProperties.isValidAddBackOfficeAgentRequest (request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        Role role = repository.findByName(RoleName.ROLE_BACK_OFFICE_AGENT).orElse(null);
        if (role == null) {
            return new ResponseEntity<>(new GenericApiError("could not load users Role", 110), HttpStatus.NOT_FOUND);
        }
        String pin = RandomStringUtils.random(4, false, true);
        UserBackOfficeAgent agent = new UserBackOfficeAgent();
        agent.setEmail(request.getEmail());
        agent.setFirstName(request.getFirstName());
        agent.setLastName(request.getLastName());
        agent.setMobileNumber(request.getMobileNumber());

        agent.setUserId(stringGeneratorUtility.fetchValidUserId(RoleName.ROLE_BACK_OFFICE_ADMIN));
        agent.setResetPin(true);
        agent.setPassword(passwordEncoder.encode(pin));
        agent.setRoles(Collections.singletonList(role));
        agent.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        agent.setUsername(request.getEmail());
        String emailMessage = "You Have Successfully received Your Password  Your new Pin Is "+ pin;
        this.processGetPasswordNotifications(agent.getEmail(),emailMessage);
        UserBackOfficeAgent savedAgent = userBackOfficeAgentRepository.save(agent);
        //////////////////////assign Permissions////////////////////////
        assignPermissionsByRoleToUser(RoleName.ROLE_BACK_OFFICE_AGENT,savedAgent);
        return ResponseEntity.ok(new GenericApiResponse("Backend Agent  Created Successfully"+"Your password have been sent through Email"));
    }
    // changing Backend agent Status
    public ResponseEntity changeBackOfficeAgentStatus(String UserId, boolean status) {
        UserBackOfficeAgent userBackOfficeAgent = userBackOfficeAgentRepository.findById(UserId).orElse(null);
        if (userBackOfficeAgent == null) {
            return new ResponseEntity<>(new GenericApiError("Office Agent with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
        userBackOfficeAgent.setEnabled(status);
        userBackOfficeAgentRepository.save(userBackOfficeAgent);
        return ResponseEntity.ok(new GenericApiResponse("Office Agent Status Successfully Updated"));

    }
    // //Get all the Backend Agents
    public ResponseEntity getAllBackOfficeAgents(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<UserBackOfficeAgent> failures = userBackOfficeAgentRepository.findAll(pageable);

        List<UserBackOfficeAgentResponse> ticketFailureResponses = failures.stream().map(this::mapBackOfficeAgentEntityToResponse).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }
    // get Agent by Id
    public ResponseEntity getBackAgentById(String userId) {

        UserBackOfficeAgent agent = userBackOfficeAgentRepository.findById(userId).orElse(null);
        if (agent == null) {
            return new ResponseEntity<>(new GenericApiError("Could Not Find Agent Admin", 110), HttpStatus.NOT_FOUND);
        }
        UserBackOfficeAgentResponse response = this.mapBackOfficeAgentEntityToResponse(agent);

        return ResponseEntity.ok(response);

    }
    //get  active Backend Agents
    public ResponseEntity getActiveBackOfficeAgents() {
        List<UserBackOfficeAgent> agents = userBackOfficeAgentRepository.findByEnabled(true);
        List<UserBackOfficeAgentResponse> userSummaries = agents.stream().map(this::mapBackOfficeAgentEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }
    //update Backend Agents
    public ResponseEntity updateBackOfficeAgent(UpdateUserBackOfficeAgentRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateBackOfficeAgentRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        UserBackOfficeAgent agent = userBackOfficeAgentRepository.findById(request.getUserId()).orElse(null);
        if(agent==null){
            return new ResponseEntity<>(new GenericApiError("Could Not Load Shop Admin Profile",110), HttpStatus.NOT_FOUND);
        }
        agent.setEmail(request.getEmail());
        agent.setFirstName(request.getFirstName());
        agent.setLastName(request.getLastName());
        agent.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        agent.setUsername(request.getEmail());
        agent.setGender(request.getGender());
        userBackOfficeAgentRepository.save(agent);
        return ResponseEntity.ok(new GenericApiResponse("Backend Agent Updated Successfully"));
    }

///////////////// Customers or Blood Donors////////////////////////////////////////////////////////////////////

    //////////  Create Donor Customer///////////////

    public ResponseEntity createUserDonor(AddUserDonorRequest request ,String userId) {

        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);

        ResponseEntity theResponse = validateUserProperties.isValidCustomerDonorRequest (request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        Role role = repository.findByName(RoleName.ROLE_CUSTOMER_DONOR).orElse(null);
        if (role == null) {
            return new ResponseEntity<>(new GenericApiError("could not load users Role", 110), HttpStatus.NOT_FOUND);
        }
        String pin = RandomStringUtils.random(4, false, true);
        UserDonor donor = new UserDonor();

        donor.setEmail(request.getEmail());
        donor.setFirstName(request.getFirstName());
        donor.setLastName(request.getLastName());
        donor.setBloodGroup(bloodGroup);
        donor.setMobileNumber(request.getMobileNumber());
        donor.setNationalID(request.getNationalID());
        donor.setAge(request.getAge());


        donor.setUserId(stringGeneratorUtility.fetchValidUserId(RoleName.ROLE_CUSTOMER_DONOR));
        donor.setResetPin(true);
        donor.setPassword(passwordEncoder.encode(pin));
        donor.setRoles(Collections.singletonList(role));
        donor.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        donor.setUsername(request.getEmail());
        UserDonor savedDonor = userDonorRepository.save(donor);
        String emailMessage = "You Have Successfully received Your Password  Your new Pin Is "+ pin;
        this.processGetPasswordNotifications(donor.getEmail(),emailMessage);
        DonorLogs donorLogs = new DonorLogs();
        UserDonor d = userDonorRepository.findById(userId).orElse(null);

         donorLogRepository.save(donorLogs);




        //////////////////////assign Permissions////////////////////////
        assignPermissionsByRoleToUser(RoleName.ROLE_CUSTOMER_DONOR,savedDonor);



        return ResponseEntity.ok(new GenericApiResponse("Customer Donor  Created Successfully"+"Your Password have been sent through email"));

    }
       //get Back Customer by status
    public ResponseEntity getCustomerDonorByStatus(boolean status, int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<UserDonor> userDonor = userDonorRepository.findByEnabled(status, pageable);
        List<UserDonorResponse> userSummaries = userDonor.stream().map(this::mapUserDonorResponseEntityToResponse).collect(toList());
        return ResponseEntity.ok(new PagedResponse<>(userSummaries, userDonor.getNumber(),
                userDonor.getSize(), userDonor.getTotalElements(), userDonor.getTotalPages(), userDonor.isLast()));
    }
    // changing  Customer activate /deactivate Status
    public ResponseEntity changeDonorStatus(String UserId, boolean status) {
        UserDonor userDonor = userDonorRepository.findById(UserId).orElse(null);
        if (userDonor == null) {
            return new ResponseEntity<>(new GenericApiError("Donor with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
        userDonor.setEnabled(status);
        userDonorRepository.save(userDonor);
        return ResponseEntity.ok(new GenericApiResponse("Customer Status Successfully Updated"));

    }
    // Approve or Reject Donor Status
    public ResponseEntity approveOrRejectDonorStatus(String UserId, boolean status) {
        UserDonor userDonor = userDonorRepository.findById(UserId).orElse(null);
        if (userDonor == null) {
            return new ResponseEntity<>(new GenericApiError("Donor with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
            userDonor.setApproved(status);
           userDonorRepository.save(userDonor);
         if(!status){
            return ResponseEntity.ok(new GenericApiResponse("Donor Status Rejected"));

         }


            return ResponseEntity.ok(new GenericApiResponse("Donor Status Successfully Approved"));


    }
    // //Get all Customers
    public ResponseEntity getAllCustomers(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<UserDonor> failures = userDonorRepository.findAll(pageable);

        List<UserDonorResponse> ticketFailureResponses = failures.stream().map(this::mapUserDonorResponseEntityToResponse).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }
    // get Customers by Id
    public ResponseEntity getCustomerDonorById(String userId) {
        UserDonor donor = userDonorRepository.findById(userId).orElse(null);
        if (donor == null) {
            return new ResponseEntity<>(new GenericApiError("Could Not find the customer", 110), HttpStatus.NOT_FOUND);
        }
        UserDonorResponse response = this.mapUserDonorResponseEntityToResponse(donor);

        return ResponseEntity.ok(response);

    }
    //get  active Customers
    public ResponseEntity getActiveCustomers() {
        List<UserDonor> agents = userDonorRepository.findByEnabled(true);
        List<UserDonorResponse> userSummaries = agents.stream().map(this::mapUserDonorResponseEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }
    /////////////////////Update Customer Donor////////////////////////
    public ResponseEntity updateCustomer(UpdateUserDonorRequest request,String loggedInUserId ){

        if(!this.isLoggedInUserDonor(loggedInUserId)){
            return new ResponseEntity<>(new GenericApiError("You are not authorized to perform this action",102), HttpStatus.UNAUTHORIZED);
        }

        ResponseEntity theResponse = validateUserProperties.isValidUpdateCustomerDonorRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        UserOrganizationAgent organizationAgent = organisationAgentRepository.findById(request.getUserId()).orElse(null);
        UserDonor donor = userDonorRepository.findById(request.getUserId()).orElse(null);
        if(donor==null){
            return new ResponseEntity<>(new GenericApiError("Could Customer Profile",110), HttpStatus.NOT_FOUND);
        }
        donor.setEmail(request.getEmail());
        donor.setFirstName(request.getFirstName());
        donor.setLastName(request.getLastName());
        donor.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        donor.setUsername(request.getEmail());
        donor.setGender(request.getGender());
        userDonorRepository.save(donor);



        return ResponseEntity.ok(new GenericApiResponse("Customer Updated Successfully"));
    }
    public  ResponseEntity donateBlood( AddDonateBlood request){

        ResponseEntity theResponse = validateUserProperties.isValidDonateBlood (request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        UserDonor userDonor = userDonorRepository.findById(request.getBloodDonorId()).orElse(null);
        if(userDonor==null){

            return new ResponseEntity<>(new GenericApiError("Could not load Blood Donor Id ",110), HttpStatus.EXPECTATION_FAILED);
        }


        Organisation organisation = organisationRepository.findById(request.getOrganisationId()).orElse(null);

        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);
        if(bloodGroup==null){

            return new ResponseEntity<>(new GenericApiError(" Blood group not available in the stock ",405), HttpStatus.EXPECTATION_FAILED);

        }
        if(!Objects.equals(userDonor.getBloodGroup().getBloodId(), bloodGroup.getBloodId())){

            return new ResponseEntity<>(new GenericApiError("Could not Load Donor Blood group Id ",405), HttpStatus.EXPECTATION_FAILED);


        }

        StockDetails stockDetails = new StockDetails();

        DonateBlood donateBlood = new DonateBlood();

        donateBlood.setDonors(userDonor);
        donateBlood.setOrganisation(organisation);
        donateBlood.setDonatedQuantity(request.getDonatedQuantity());
        donateBlood.setBloodGroupId(bloodGroup.getBloodId());
        donateBloodRepository.save(donateBlood);

        DonorLogs donorLogs = new DonorLogs();
        donorLogs.setDonorId(userDonor.getUserId());
        donorLogs.setLocal(LocalDate.now());
        donorLogs.setOrganizationId(organisation.getId());
        donorLogRepository.save(donorLogs);

           stockDetails.setBloodGroupId(bloodGroup.getBloodId());
           stockDetails.setDonatedQuantity(request.getDonatedQuantity());
           stockDetails.setDonorId(userDonor.getUserId());
           stockDetailsRepository.save(stockDetails);

           if(userDonor.getBloodGroup().getBloodId()==bloodGroup.getBloodId()){

               bloodGroup.setTotalQuantity(bloodGroup.getTotalQuantity()+donateBlood.getDonatedQuantity());
               bloodGroupRepository.save(bloodGroup);
           }

        return ResponseEntity.ok(new GenericApiResponse("You have Successfully donated blood"));


    }

    //////////////////////////////////////OrganizationAgent////////////////////////////////////////////

    //////////  Create Organisation Agent///////////////

    public ResponseEntity createOrganisationAgent(AddUserOrganizationAgentRequest request) {

        ResponseEntity theResponse = validateUserProperties.isValidOrganisationAgentRequest (request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        Organisation organisation =  organisationRepository.findById(request.getOrganisationId()).orElse(null);
        Role role = repository.findByName(RoleName.ROLE_ORGANIZATION_AGENT).orElse(null);
        if (role == null) {
            return new ResponseEntity<>(new GenericApiError("could not load users Role", 110), HttpStatus.NOT_FOUND);
        }
        String pin = RandomStringUtils.random(4, false, true);
        UserOrganizationAgent organizationAgent = new UserOrganizationAgent();

        organizationAgent.setEmail(request.getEmail());
        organizationAgent.setFirstName(request.getFirstName());
        organizationAgent.setLastName(request.getLastName());
        organizationAgent.setMobileNumber(request.getMobileNumber());
        organizationAgent.setOrganisation(organisation);
        organizationAgent.setAddress(request.getAddress());
        organizationAgent.setDesignation(request.getDesignation());
        organizationAgent.setUserId(stringGeneratorUtility.fetchValidUserId(RoleName.ROLE_ORGANIZATION_AGENT));
        organizationAgent.setResetPin(true);
        organizationAgent.setPassword(passwordEncoder.encode(pin));
        organizationAgent.setRoles(Collections.singletonList(role));
        organizationAgent.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        organizationAgent.setUsername(request.getEmail());
        UserOrganizationAgent savedAgent = organisationAgentRepository.save(organizationAgent);

        //////////////////////assign Permissions////////////////////////
        assignPermissionsByRoleToUser(RoleName.ROLE_ORGANIZATION_AGENT,savedAgent);
        String emailMessage = "You Have Successfully received Your Password  Your new Pin Is "+ pin;
        this.processGetPasswordNotifications(organizationAgent.getEmail(),emailMessage);
        return ResponseEntity.ok(new GenericApiResponse("Organisation agent  Created Successfully"+"Password have been sent through Emil"));
    }
    /////////////////////Update Organisation Agent////////////////////////
    public ResponseEntity updateOrganisationAgent(UpdateUserOrganizationAgentRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateOrganisationAgentRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        UserOrganizationAgent agent = organisationAgentRepository.findById(request.getUserId()).orElse(null);
        if(agent==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Organisation Agent Profile",110), HttpStatus.NOT_FOUND);
        }
        agent.setEmail(request.getEmail());
        agent.setFirstName(request.getFirstName());
        agent.setLastName(request.getLastName());
        agent.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        agent.setUsername(request.getEmail());
        agent.setGender(request.getGender());
        organisationAgentRepository.save(agent);

        return ResponseEntity.ok(new GenericApiResponse("Organisation agent Updated Successfully"));
    }
    //get Organisation Agent by status
    public ResponseEntity getOrganisationAgentByStatus(boolean status, int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<UserOrganizationAgent> useragent = organisationAgentRepository.findByEnabled(status, pageable);
        List<UserOrganisationAgentResponse> userSummaries = useragent.stream().map(this::mapUserOrganisationAgentEntityToResponse).collect(toList());
        return ResponseEntity.ok(new PagedResponse<>(userSummaries, useragent.getNumber(),
                useragent.getSize(), useragent.getTotalElements(), useragent.getTotalPages(), useragent.isLast()));

    }
    // changing Organisation Agent activate /deactivate Status
    public ResponseEntity changeOrganisationAgentStatus(String UserId, boolean status) {
        UserOrganizationAgent  agent= organisationAgentRepository.findById(UserId).orElse(null);
        if (agent == null) {
            return new ResponseEntity<>(new GenericApiError("Organisation Agent with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
        agent.setEnabled(status);
        organisationAgentRepository.save(agent);
        return ResponseEntity.ok(new GenericApiResponse("Agent Status Successfully Updated"));

    }
    // //Get all Organisation Agents
    public ResponseEntity getAllOrganisationAgent(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<UserOrganizationAgent> failures = organisationAgentRepository.findAll(pageable);

        List<UserOrganisationAgentResponse> ticketFailureResponses = failures.stream().map(this::mapUserOrganisationAgentEntityToResponse).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }
    // get organisation Agent by Id
    public ResponseEntity getOrganisationById(String userId) {
        UserOrganizationAgent agent = organisationAgentRepository.findById(userId).orElse(null);
        if (agent == null) {
            return new ResponseEntity<>(new GenericApiError("Could Not find the Agent", 110), HttpStatus.NOT_FOUND);
        }
        UserOrganisationAgentResponse response = this.mapUserOrganisationAgentEntityToResponse(agent);

        return ResponseEntity.ok(response);

    }
    //get  active organisation agents
    public ResponseEntity getActiveOrganisationAgents() {
        List<UserOrganizationAgent> agents = organisationAgentRepository.findByEnabled(true);
        List<UserOrganisationAgentResponse> userSummaries = agents.stream().map(this::mapUserOrganisationAgentEntityToResponse).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }

///////////////////////////////////Blood Recipient///////////////////////////////////////////////////////////////

    public ResponseEntity createBloodRecipient(BloodRecipientRequest request) {

        ResponseEntity theResponse = validateUserProperties.isValidBloodRecipientRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        BloodGroup bloodGroup = bloodGroupRepository.findById(request.getBloodGroupId()).orElse(null);


        Role role = repository.findByName(RoleName.ROLE_RECIPIENT).orElse(null);
        if (role == null) {
            return new ResponseEntity<>(new GenericApiError("could not load users Role", 110), HttpStatus.NOT_FOUND);
        }
        String pin = RandomStringUtils.random(4, false, true);

        BloodRecipient bloodRecipient = new BloodRecipient();

        bloodRecipient.setEmail(request.getEmail());
        bloodRecipient.setFirstName(request.getFirstName());
        bloodRecipient.setLastName(request.getLastName());
        bloodRecipient.setAddress(request.getAddress());
        bloodRecipient.setPhoneNumber(request.getPhoneNumber());
        bloodRecipient.setIdNumber(request.getIdNumber());
        bloodRecipient.setBloodGroup(bloodGroup);


        bloodRecipient.setUserId(stringGeneratorUtility.fetchValidUserId(RoleName.ROLE_RECIPIENT));
        bloodRecipient.setResetPin(true);
        bloodRecipient.setPassword(passwordEncoder.encode(pin));
        bloodRecipient.setRoles(Collections.singletonList(role));
        bloodRecipient.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        bloodRecipient.setUsername(request.getEmail());
        BloodRecipient saveRecipient = bloodRecipientRepositorry.save(bloodRecipient);
        //////////////////////assign Permissions////////////////////////
        assignPermissionsByRoleToUser(RoleName.ROLE_RECIPIENT,saveRecipient);
        String emailMessage = "You Have Successfully received Your Password  Your new Pin Is "+ pin;
        this.processGetPasswordNotifications(bloodRecipient.getEmail(),emailMessage);
        return ResponseEntity.ok(new GenericApiResponse("Blood Recipient Created Successfully"+"Your Password have been sent through Email"));
    }
    /////////////////////Update recipient////////////////////////
    public ResponseEntity updateBloodRecipient(UpdateBloodRecipientRequest request){

        ResponseEntity theResponse = validateUserProperties.isValidUpdateBloodRecipientRequest(request);
        if (theResponse.getStatusCode().value() != 200) {
            return theResponse;
        }
        BloodRecipient recipient = bloodRecipientRepositorry.findById(request.getUserId()).orElse(null);
        if(recipient==null){
            return new ResponseEntity<>(new GenericApiError("Could not load Organisation Agent Profile",110), HttpStatus.NOT_FOUND);
        }
        recipient.setEmail(request.getEmail());
        recipient.setFirstName(request.getFirstName());
        recipient.setLastName(request.getLastName());
        recipient.setPhoneNumber(request.getPhoneNumber());
        recipient.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        recipient.setUsername(request.getEmail());

        bloodRecipientRepositorry.save(recipient);
        return ResponseEntity.ok(new GenericApiResponse("recipient Updated Successfully"));
    }
    //get recipient by status
    public ResponseEntity getBloodRecipientByStatus(boolean status, int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<BloodRecipient> useragent = bloodRecipientRepositorry.findByEnabled(status, pageable);
        List<BloodRecipientResponse> userSummaries = useragent.stream().map(this::mapBloodRecipientResponseEntity).collect(toList());
        return ResponseEntity.ok(new PagedResponse<>(userSummaries, useragent.getNumber(),
                useragent.getSize(), useragent.getTotalElements(), useragent.getTotalPages(), useragent.isLast()));

    }
    // changing recipient activate /deactivate Status
    public ResponseEntity changeBloodRecipientStatus(String UserId, boolean status) {
            BloodRecipient  bloodRecipient= bloodRecipientRepositorry.findById(UserId).orElse(null);
        if (bloodRecipient== null) {
            return new ResponseEntity<>(new GenericApiError("Blood recipient with Provided Id Does not  Exist!", 110), HttpStatus.EXPECTATION_FAILED);
        }
        bloodRecipient.setEnabled(status);
        bloodRecipientRepositorry.save(bloodRecipient);
        return ResponseEntity.ok(new GenericApiResponse("Recipient Status Successfully Updated"));

    }
    // //Get all blood recipients
    public ResponseEntity getAllBloodRecipients(int page, int size) {
        formatUtility.validatePageNumberAndSize(page, size);
        // Retrieve events
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        // Retrieve events

        Page<BloodRecipient> failures = bloodRecipientRepositorry.findAll(pageable);

        List<BloodRecipientResponse> ticketFailureResponses = failures.stream().map(this::mapBloodRecipientResponseEntity).collect(toList());

        return ResponseEntity.ok(new PagedResponse<>(ticketFailureResponses, failures.getNumber(),
                failures.getSize(), failures.getTotalElements(), failures.getTotalPages(), failures.isLast()));

    }
    // get Recipient by Id
    public ResponseEntity getRecipientById(String userId) {
        BloodRecipient bloodRecipient = bloodRecipientRepositorry.findById(userId).orElse(null);
        if (bloodRecipient == null) {
            return new ResponseEntity<>(new GenericApiError("Could Not find the Agent", 110), HttpStatus.NOT_FOUND);
        }
        BloodRecipientResponse response = this.mapBloodRecipientResponseEntity(bloodRecipient);

        return ResponseEntity.ok(response);

    }
    //get  active Recipients
    public ResponseEntity getActiveRecipients() {
        List<BloodRecipient> recipients = bloodRecipientRepositorry.findByEnabled(true);
        List<BloodRecipientResponse> userSummaries = recipients.stream().map(this::mapBloodRecipientResponseEntity).collect(toList());
        return ResponseEntity.ok(userSummaries);
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// Inside Methods////////////////////////////////////////////////////
    //mapping BackOfficeAdminsResponse to the UserAdmin Entity
    public UserBackOfficeAdminResponse mapBackOfficeAdminEntityToResponse(UserBackOfficeAdmin admin) {

        UserBackOfficeAdminResponse response = new UserBackOfficeAdminResponse();
        response.setEmail(admin.getEmail());
        response.setFirstName(admin.getFirstName());
        response.setLastName(admin.getLastName());
        response.setUserId(admin.getUserId());
        response.setGender(admin.getGender());
        response.setActive(admin.getEnabled());
        if (admin.getPermissions() != null) {

            response.setPermissions(this.getPermissions(admin));


        }

        return response;
    }
    //mapping BackOfficeAgentResponse to the UserAdmin Entity
    public UserBackOfficeAgentResponse mapBackOfficeAgentEntityToResponse(UserBackOfficeAgent agent) {



        UserBackOfficeAgentResponse response= new UserBackOfficeAgentResponse();
        response.setEmail(agent.getEmail());
        response.setFirstName(agent.getFirstName());
        response.setLastName(agent.getLastName());
        response.setUserId(agent.getUserId());
        response.setGender(agent.getGender());

        response.setMobileNumber(agent.getMobileNumber());
        response.setActive(agent.getEnabled());
        if (agent.getPermissions() != null) {

            response.setPermissions(this.getPermissions(agent));


        }

        return response;
    }
    /////////////  //mapping UserDonorResponse  to the UserDonorEntity///////////////////
    public UserDonorResponse mapUserDonorResponseEntityToResponse(UserDonor donor) {
        UserDonorResponse response= new UserDonorResponse();
        if(donor.getBloodGroup()!=null){

            response.setBloodGroup(organisationService.mapBloodGroupEntityToResponse(donor.getBloodGroup()));
        }
        response.setEmail(donor.getEmail());
        response.setFirstName(donor.getFirstName());
        response.setLastName(donor.getLastName());
        response.setUserId(donor.getUserId());
        response.setDonorStatus(donor.getApproved());
        response.setNationalID(response.getNationalID());
        response.setMobileNumber(donor.getMobileNumber());
        response.setActive(donor.getEnabled());
        if (donor.getPermissions() != null) {

            response.setPermissions(this.getPermissions(donor));


        }

        return response;
    }
    /////////////  //mapping UserDonorResponse  to the UserDonorEntity///////////////////
    public BloodRecipientResponse mapBloodRecipientResponseEntity(BloodRecipient bloodRecipient) {



        BloodRecipientResponse response= new BloodRecipientResponse();
        if(bloodRecipient.getBloodGroup()!=null){

            response.setBloodGroup(organisationService.mapBloodGroupEntityToResponse(bloodRecipient.getBloodGroup()));
        }
        response.setEmail(bloodRecipient.getEmail());
        response.setFirstName(bloodRecipient.getFirstName());
        response.setLastName(bloodRecipient.getLastName());
        response.setUserId(bloodRecipient.getUserId());
        response.setIdNumber(bloodRecipient.getIdNumber());
        response.setGender(bloodRecipient.getGender());
        response.setAddress(bloodRecipient.getAddress());
        response.setPhoneNumber(bloodRecipient.getPhoneNumber());
        response.setActive(bloodRecipient.getEnabled());
        if (bloodRecipient.getPermissions() != null) {

            response.setPermissions(this.getPermissions(bloodRecipient));


        }

        return response;
    }
    /////////////  //mapping User Organisation agent Response to Entity ///////////////////
    public UserOrganisationAgentResponse mapUserOrganisationAgentEntityToResponse(UserOrganizationAgent organizationAgent) {


        UserOrganisationAgentResponse agentResponse = new UserOrganisationAgentResponse();
        if(organizationAgent.getOrganisation()!=null){
            agentResponse.setOrganisation(organisationService.mapOrganisationEntityToResponse(organizationAgent.getOrganisation()));
        }
        agentResponse.setEmail(organizationAgent.getEmail());
        agentResponse.setAddress(organizationAgent.getAddress());
        agentResponse.setDesignation(organizationAgent.getDesignation());
        agentResponse.setFirstName(organizationAgent.getFirstName());
        agentResponse.setLastName(organizationAgent.getLastName());
        agentResponse.setUserId(organizationAgent.getUserId());
        agentResponse.setGender(organizationAgent.getGender());
        agentResponse.setMobileNumber(organizationAgent.getMobileNumber());
        agentResponse.setActive(organizationAgent.getEnabled());
        if (organizationAgent.getPermissions() != null) {

            agentResponse.setPermissions(this.getPermissions(organizationAgent));


        }

        return agentResponse;
    }

    //////User Permissions and privileges ///////////
    private List<PermissionResponse> getPermissions(User user) {
        final List<PermissionResponse> responses = new CopyOnWriteArrayList<>();
        final Collection<Permission> collection = user.getPermissions();

        for (final Permission item : collection) {
            List<Privilege> privileges = item.getPrivileges().stream().filter(x -> user.getPrivileges().contains(x)).collect(toList());
            List<PrivilegeResponse> privs = new CopyOnWriteArrayList<>();
            for (Privilege p : privileges) {
                PrivilegeResponse privilegeResponse = new PrivilegeResponse();
                privilegeResponse.setId(p.getId());
                privilegeResponse.setName(p.getName());
                privs.add(privilegeResponse);
            }
            PermissionResponse response = new PermissionResponse();
            response.setName(item.getName());
            response.setId(item.getId());
            response.setPriviledges(privs);
            responses.add(response);
        }

        return responses;

    }
    public void assignPermissionsByRoleToUser(RoleName roleName,User user) {
        Role role = repository.findByName(roleName).orElse(null);
        List<Privilege> privileges = new CopyOnWriteArrayList<>();

        List<Permission> permissions = permissionRepository.findByRoles_Id(role.getId());
        for(Permission p: permissions){
            for(Privilege privilege: p.getPrivileges()){
                if(!privileges.contains(privilege)){
                    privileges.add(privilege);
                }
            }

        }
        user.setPermissions(permissions);
        user.setPrivileges(privileges);
        userRepository.save(user);

    }

    ///////////////////////////Email/////////////////////////////////////////////////////////////////
    private void processGetPasswordNotifications(String email,String emailMessage) {

        Mail mail = new Mail();//replace with your desired email
        mail.setMailTo(email);//replace with your desired email
        mail.setSubject("Blood Donor Management Password Creation");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("headerText", "You Have Successfully received Your Password");

        model.put("appName","Blood Donor System" );
        mail.setSubject("Password have been created Successfully For "+ "Blood Management System");


        model.put("footerText","");

        model.put("emailText",emailMessage);

        mail.setProps(model);
        emailExecutor.ScheduledMailExecutor(mail,"general",1);

    }
    private boolean isLoggedInUserDonor(String loggedInUserId ){

        UserDonor userDonor = userDonorRepository.findById(loggedInUserId).orElse(null);

        if(userDonor!=null){
            return true;
        }
        else{

            return false;
        }


    }

}

