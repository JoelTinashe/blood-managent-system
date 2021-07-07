package com.digitalkrapht.bloodbank.bloodbank.authentication.service;

import com.digitalkrapht.bloodbank.bloodbank.authentication.request.LoginRequest;
import com.digitalkrapht.bloodbank.bloodbank.authentication.response.JwtAuthenticationResponse;
import com.digitalkrapht.bloodbank.bloodbank.authentication.response.UserSummary;
import com.digitalkrapht.bloodbank.bloodbank.nofications.email.executor.EmailExecutor;
import com.digitalkrapht.bloodbank.bloodbank.nofications.email.request.Mail;
import com.digitalkrapht.bloodbank.bloodbank.security.jwt.JwtTokenProvider;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Permission;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Privilege;
import com.digitalkrapht.bloodbank.bloodbank.security.models.RoleName;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PermissionResponse;
import com.digitalkrapht.bloodbank.bloodbank.security.response.PrivilegeResponse;
import com.digitalkrapht.bloodbank.bloodbank.users.models.User;
import com.digitalkrapht.bloodbank.bloodbank.users.models.UserDonor;
import com.digitalkrapht.bloodbank.bloodbank.users.repository.UserDonorRepository;
import com.digitalkrapht.bloodbank.bloodbank.users.repository.UserRepository;
import com.digitalkrapht.bloodbank.bloodbank.utils.format.FormatUtility;
import com.digitalkrapht.bloodbank.bloodbank.utils.generators.StringGeneratorUtility;
import com.digitalkrapht.bloodbank.bloodbank.utils.responcse.GenericApiError;
import com.digitalkrapht.bloodbank.bloodbank.utils.responcse.GenericApiResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.stream.Collectors.toList;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDonorRepository userDonorRepository;

    @Autowired
    EmailExecutor emailExecutor;
    @Autowired
    FormatUtility formatUtility;
    @Autowired
    StringGeneratorUtility stringGeneratorUtility;
    public ResponseEntity Login(LoginRequest loginRequest) {



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        User theUser = userRepository.findByUsernameOrEmail(loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail()).orElse(null);
        if(theUser==null){
            JwtAuthenticationResponse myAuthResponse = new JwtAuthenticationResponse();
            myAuthResponse.setMessage("Something's happened!");
            return new ResponseEntity<>(myAuthResponse, HttpStatus.EXPECTATION_FAILED);
        }


        if (theUser.getEnabled()) {
            String jwt = tokenProvider.generateToken(authentication);
            UserSummary summary = this.mapUserEntityToSpecificSummary(theUser);
            if(summary==null){
                return new ResponseEntity<>(new GenericApiError("User Account Is Corrupted Please Contact Administrator",113), HttpStatus.EXPECTATION_FAILED);
            }
            summary.setAccessToken("Bearer " + jwt);
            theUser.setLoginCount(theUser.getLoginCount()+1);
            theUser.setLastLogin(LocalDateTime.now());
            userRepository.save(theUser);
            return ResponseEntity.ok(summary);
        }


        JwtAuthenticationResponse myAuthResponse = new JwtAuthenticationResponse();
        myAuthResponse.setMessage("Account locked, contact admin.");
        return new ResponseEntity<>(myAuthResponse, HttpStatus.LOCKED);
    }
    public ResponseEntity resetPasswordWithEmail(String email){
        if(!formatUtility.isValidEmail(email)){
            return new ResponseEntity<>(new GenericApiError("Invalid Email",106), HttpStatus.EXPECTATION_FAILED);
        }
        User theUser = userRepository.findByEmail(email).orElse(null);
        if(theUser==null){
            return new ResponseEntity<>(new GenericApiError("Email is not registered on this platform",110), HttpStatus.NOT_FOUND);
        }
        String pin = RandomStringUtils.random(4,false,true);
        theUser.setPassword(passwordEncoder.encode(pin));
        theUser.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
        theUser.setResetPin(true);
        userRepository.save(theUser);
        String emailMessage = "You Have Successfully Reset Your Password  Your new Pin Is "+ pin;
        this.processResetPasswordNotifications(theUser.getEmail(),emailMessage);
        return ResponseEntity.ok(new GenericApiResponse("Password Reset Successfully"));
    }
    private void processResetPasswordNotifications(String email,String emailMessage) {

        Mail mail = new Mail();//replace with your desired email
        mail.setMailTo(email);//replace with your desired email
        mail.setSubject("Blood Donor Management Reset Password");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("headerText", "You Have Successfully Reset Your Password");

        model.put("appName","Blood Donor System" );
        mail.setSubject("Password Changed Successfully For "+ "Blood Management System");


        model.put("footerText","Blood System Management");

        model.put("emailText",emailMessage);

        mail.setProps(model);
        emailExecutor.ScheduledMailExecutor(mail,"general",1);

    }
   // Mapping user user entity to specific Summary
    public UserSummary mapUserEntityToSpecificSummary(User theUser) {
        //== respond to customer
        UserSummary userSummary = this.SetBasicSummary(theUser);
        if (theUser.getRoles().stream().anyMatch(str -> str.getName().equals(RoleName.ROLE_CUSTOMER_DONOR))) {
            UserDonor specificUser = userDonorRepository.findById(theUser.getUserId()).orElse(null);
            if(specificUser==null){
                return null;
            }
            userSummary.setMobileNumber(specificUser.getMobileNumber());

        }

        return userSummary;
    }
    //mapping user to user summary
    public UserSummary SetBasicSummary(User myUser) {

        List<GrantedAuthority> userRoles = myUser.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(String.valueOf(role.getName()))
        ).collect(toList());

        UserSummary baseSummary = new UserSummary();
        baseSummary.setUserId(myUser.getUserId());
        baseSummary.setEmail(myUser.getEmail());
        baseSummary.setResetPin(myUser.getResetPin());
        baseSummary.setFirstName(myUser.getFirstName());
        baseSummary.setLastName(myUser.getLastName());
        baseSummary.setUsername(myUser.getUsername());
        baseSummary.setEnabled(myUser.getEnabled());
        baseSummary.setUserGroup((String.valueOf(myUser.getRoles().stream().findAny().get().getName())));
        baseSummary.setUserRoles(userRoles);
        baseSummary.setPermissions(this.getPermissions(myUser));


        return baseSummary;
    }
    // this is a method for priveleges and permissions
    private  List<PermissionResponse> getPermissions(User user) {

        final List<PermissionResponse> responses = new CopyOnWriteArrayList<>();
        final Collection<Permission> collection = user.getPermissions();

        for (final Permission item : collection) {
            List<Privilege> privileges =item.getPrivileges().stream().filter(x -> user.getPrivileges().contains(x)).collect(toList());
            List<PrivilegeResponse> privs= new CopyOnWriteArrayList<>();
            for(Privilege p: privileges){
                PrivilegeResponse privilegeResponse= new PrivilegeResponse();
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



}
