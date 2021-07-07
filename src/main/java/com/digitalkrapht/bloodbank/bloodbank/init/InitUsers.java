package com.digitalkrapht.bloodbank.bloodbank.init;

import com.digitalkrapht.bloodbank.bloodbank.security.models.Permission;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Privilege;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.security.models.RoleName;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.PermissionRepository;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.RoleRepository;
import com.digitalkrapht.bloodbank.bloodbank.users.models.*;
import com.digitalkrapht.bloodbank.bloodbank.users.repository.*;
import com.digitalkrapht.bloodbank.bloodbank.utils.format.FormatUtility;
import com.digitalkrapht.bloodbank.bloodbank.utils.generators.StringGeneratorUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

    @Component("initUsers")
    @DependsOn({"initRoles"})
    public class InitUsers {
        @Autowired
        private PermissionRepository permissionRepository;
        @Autowired
        UserSystemsAdminRepository userSystemsAdminRepository;
        @Autowired
        UserBackOfficeAdminRepository userBackOfficeAdminRepository;
        @Autowired
        UserBackOfficeAgentRepository userBackOfficeAgentRepository;
        @Autowired
        UserRepository userRepository;
        @Autowired
        StringGeneratorUtility stringGeneratorUtility;
        @Autowired
        RoleRepository roleRepository;
        @Autowired
        FormatUtility formatUtility;
        @Autowired
        PasswordEncoder passwordEncoder;
        @Autowired
        UserOrganisationAgentRepository userOrganisationAgentRepository;
        @Autowired
        UserDonorRepository userDonorRepository;
        @Autowired
        BloodRecipientRepositorry bloodRecipientRepositorry;
        private void saveUserBackend(String firstName, String surname,  String email, RoleName roleName) {

            Role role = roleRepository.findByName(roleName).orElse(null);

            if (roleName.equals(RoleName.ROLE_SYSTEM)) {
                UserSystemAdmin admin = new UserSystemAdmin();
                admin.setUserId(stringGeneratorUtility.fetchValidUserId(roleName));
                admin.setFirstName(firstName);
                admin.setLastName(surname);
                admin.setEmail(email);
                admin.setResetPin(false);
                admin.setPassword(passwordEncoder.encode("1111"));
                admin.setRoles(Collections.singletonList(role));
                admin.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
                admin.setUsername(email);
                UserSystemAdmin savedAdmin =userSystemsAdminRepository.save(admin);
                //////////////////////assign Permissions////////////////////////
                assignPermissionsByRoleToUser(RoleName.ROLE_SYSTEM,savedAdmin);
            }
            if (roleName.equals(RoleName.ROLE_BACK_OFFICE_AGENT)) {

                UserBackOfficeAgent backAgent = new UserBackOfficeAgent();
                backAgent.setUserId(stringGeneratorUtility.fetchValidUserId(roleName));
                backAgent.setFirstName(firstName);

                backAgent.setLastName(surname);
                backAgent.setEmail(email);
                backAgent.setResetPin(false);
                backAgent.setPassword(passwordEncoder.encode("1111"));
                backAgent.setRoles(Collections.singletonList(role));
                backAgent.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
               backAgent.setUsername(email);
                UserBackOfficeAgent savedAgent= userBackOfficeAgentRepository.save(backAgent);
                //////////////////////assign Permissions////////////////////////
                assignPermissionsByRoleToUser(RoleName.ROLE_BACK_OFFICE_AGENT,savedAgent);
            }
            if (roleName.equals(RoleName.ROLE_BACK_OFFICE_ADMIN)) {

                UserBackOfficeAdmin admin = new UserBackOfficeAdmin();
                admin.setUserId(stringGeneratorUtility.fetchValidUserId(roleName));
                admin.setFirstName(firstName);
                admin.setLastName(surname);
                admin.setEmail(email);
                admin.setResetPin(false);
                admin.setPassword(passwordEncoder.encode("1111"));
                admin.setRoles(Collections.singletonList(role));
                admin.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
                admin.setUsername(email);
                UserBackOfficeAdmin savedAdmin = userBackOfficeAdminRepository.save(admin);
                //////////////////////assign Permissions////////////////////////
                assignPermissionsByRoleToUser(RoleName.ROLE_BACK_OFFICE_ADMIN,savedAdmin);
            }
            if (roleName.equals(RoleName.ROLE_ORGANIZATION_AGENT)) {

                UserOrganizationAgent organizationAgent = new UserOrganizationAgent();
                organizationAgent.setUserId(stringGeneratorUtility.fetchValidUserId(roleName));
                organizationAgent.setFirstName(firstName);
                organizationAgent.setLastName(surname);
                organizationAgent.setEmail(email);
                organizationAgent.setResetPin(false);
                organizationAgent.setPassword(passwordEncoder.encode("1111"));
                organizationAgent.setRoles(Collections.singletonList(role));
                organizationAgent.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
                organizationAgent.setUsername(email);
                UserOrganizationAgent savedAgent = userOrganisationAgentRepository.save(organizationAgent);
                //////////////////////assign Permissions////////////////////////
                assignPermissionsByRoleToUser(RoleName.ROLE_ORGANIZATION_AGENT,savedAgent);
            }
            if (roleName.equals(RoleName.ROLE_CUSTOMER_DONOR)) {

                UserDonor userDonor= new UserDonor();
                userDonor.setUserId(stringGeneratorUtility.fetchValidUserId(roleName));
                userDonor.setFirstName(firstName);
                userDonor.setLastName(surname);
                userDonor.setEmail(email);
                userDonor.setResetPin(false);
                userDonor.setPassword(passwordEncoder.encode("1111"));
                userDonor.setRoles(Collections.singletonList(role));
                userDonor.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
                userDonor.setUsername(email);
                UserDonor savedDonors = userDonorRepository.save(userDonor);
                //////////////////////assign Permissions////////////////////////
                assignPermissionsByRoleToUser(RoleName.ROLE_CUSTOMER_DONOR,savedDonors);
            }
            if (roleName.equals(RoleName.ROLE_RECIPIENT)) {

                BloodRecipient bloodRecipient= new BloodRecipient();
                bloodRecipient.setUserId(stringGeneratorUtility.fetchValidUserId(roleName));
                bloodRecipient.setFirstName(firstName);
                bloodRecipient.setLastName(surname);
                bloodRecipient.setEmail(email);
                bloodRecipient.setResetPin(false);
                bloodRecipient.setPassword(passwordEncoder.encode("1111"));
                bloodRecipient.setRoles(Collections.singletonList(role));
                bloodRecipient.setTokenHash(stringGeneratorUtility.fetchValidTokenHash());
                bloodRecipient.setUsername(email);
                BloodRecipient saveRecipient = bloodRecipientRepositorry.save(bloodRecipient);
                //////////////////////assign Permissions////////////////////////
                assignPermissionsByRoleToUser(RoleName.ROLE_RECIPIENT,saveRecipient);
            }





        }


        @PostConstruct
        private void InitializeUsers(){

            if (userRepository.findAll().isEmpty()) {
                //===system
                this.saveUserBackend("john", "mashawi", "joel.mashawi@gmail.com",
                        RoleName.ROLE_BACK_OFFICE_ADMIN);
                this.saveUserBackend("Shingi", "Digi", "freddy@digitalkrapht.com",
                        RoleName.ROLE_BACK_OFFICE_AGENT);
                this.saveUserBackend("Freddy", "Mutonga", "khumutonga@gmail.com",
                        RoleName.ROLE_RECIPIENT);
                this.saveUserBackend("Chiedza", "Daly", "chiedza@gmail.com",
                        RoleName.ROLE_CUSTOMER_DONOR);
                this.saveUserBackend("Chiedza", "Daly", "chi@gmail.com",
                        RoleName.ROLE_ORGANIZATION_AGENT);




            }


        }


        public void assignPermissionsByRoleToUser(RoleName roleName, User user) {
            Role role = roleRepository.findByName(roleName).orElse(null);
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
}
