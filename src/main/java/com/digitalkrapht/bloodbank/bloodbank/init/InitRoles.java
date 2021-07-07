package com.digitalkrapht.bloodbank.bloodbank.init;

import com.digitalkrapht.bloodbank.bloodbank.security.models.Permission;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Privilege;
import com.digitalkrapht.bloodbank.bloodbank.security.models.Role;
import com.digitalkrapht.bloodbank.bloodbank.security.models.RoleName;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.PermissionRepository;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.PrivilegeRepository;
import com.digitalkrapht.bloodbank.bloodbank.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component("initRoles")
public class InitRoles {

    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PrivilegeRepository privilegeRepository;

    private Privilege savePrivilege(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    private void saveNewRole(final RoleName name, String description, boolean selfRegEnabled, final Collection<Permission> permissions) {
        Role role = roleRepository.findByName(name).orElse(null);
        if (role == null) {
            role = new Role();
        }
        role.setName(name);
        role.setDescription(description);
        role.setPermissions(permissions);
        role.setSelfRegEnabled(selfRegEnabled);
        roleRepository.save(role);
    }

    private Permission saveNewPermission(final String name, final Collection<Privilege> privileges) {
        Permission role = new Permission();
        role.setName(name);
        role.setPrivileges(privileges);
        return permissionRepository.save(role);
    }

    @PostConstruct
    private void InitializeRolesAndPrivileges() {
        if (roleRepository.findAll().isEmpty()) {

            // == system Admin privileges
            final Privilege createAppVariables = savePrivilege("UPDATE_APP_VARIABLES");
            // == backend Admin privileges
            final Privilege createBackendAdmins = savePrivilege("CREATE_BACKEND_ADMINS");
            final Privilege updateBackendAdmins = savePrivilege("EDIT_BACKEND_ADMINS");
            final Privilege viewBackendAdmins = savePrivilege("VIEW_BACKEND_ADMINS");
            final Privilege activateBackendAdmins = savePrivilege("ACTIVATE_BACKEND_ADMIN");
            //==system AGENTS privileges

            final Privilege createBackendAgents = savePrivilege("CREATE_BACKEND_AGENTS");
            final Privilege activateBackendAgents = savePrivilege("ACTIVATE_BACKEND_AGENTS");
            final Privilege updateBackendAgents = savePrivilege("EDIT_BACKEND_AGENTS");
            final Privilege viewBackendAgents = savePrivilege("VIEW_BACKEND_AGENTS");
            //donors privileges
            final Privilege createBloodDonor = savePrivilege("CREATE_BLOOD_DONORS");
            final Privilege activateBloodDonors = savePrivilege("ACTIVATE_BLOOD_DONORS");
            final Privilege updateDonors = savePrivilege("EDIT_BLOOD_DONORS");
            final Privilege donateBlood = savePrivilege("DONATE_BLOOD");

            ///////////////blood recipient/////////////////////

            final Privilege createBloodRecipient = savePrivilege("CREATE_BLOOD_DONORS");
            final Privilege activateBloodRecipient= savePrivilege("ACTIVATE_BLOOD_DONORS");
            final Privilege updateRecipient= savePrivilege("EDIT_BLOOD_DONORS");
            final Privilege receiveRecipient= savePrivilege("RECEIVE_BLOOD");

            //==system organisation Agent privileges

            final Privilege createOrganisationAgents = savePrivilege("CREATE_ORGANISATION_AGENTS");
            final Privilege activateOrganisationAgents = savePrivilege("ACTIVATE_ORGANISATION_AGENTS");
            final Privilege updateOrganisationAgents = savePrivilege("EDIT_ORGANISATION_AGENTS");
            final Privilege viewOrganisationAgents = savePrivilege("VIEW_ORGANISATION_AGENTS");

            //== final Privilege updateBackendAdmins = savePrivilege("EDIT_BACKEND_ADMINS");


            // == sms management privileges
           // final Privilege viewSMSLogs = savePrivilege("VIEW_SMS_LOGS");


//            final List<Privilege> backendAdminSMSManagementPriviledges =
//                    new ArrayList<Privilege>(Arrays.asList(viewSMSLogs));
            final List<Privilege> recipientUserManagementPrivileges =
                    new ArrayList<>(Arrays.asList(createBloodRecipient,  updateRecipient, activateBloodRecipient, receiveRecipient));

            final List<Privilege> donorUserManagementPrivileges =
                    new CopyOnWriteArrayList<>(Arrays.asList(createBloodDonor, updateDonors, activateBloodDonors, donateBlood));
            final List<Privilege> systemAdminAppVariableManagementPriviledges =
                    new ArrayList<Privilege>(Arrays.asList(createAppVariables));

            final List<Privilege> systemAdminUserManagementPrivileges =
                    new ArrayList<Privilege>(Arrays.asList(createBackendAdmins, updateBackendAdmins, viewBackendAdmins, activateBackendAdmins));
            final List<Privilege> systemAgentsUserManagementPrivileges =
                    new ArrayList<Privilege>(Arrays.asList(createBackendAgents, updateBackendAgents, viewBackendAgents, activateBackendAgents));

            final List<Privilege> systemOrganisationAgentsUserManagementPrivileges =
                    new ArrayList<Privilege>(Arrays.asList(createOrganisationAgents, updateOrganisationAgents, viewOrganisationAgents, activateOrganisationAgents));



            //system Organisation Agent permissions////////////////////
            Permission systemOrganisationAgentManagementPermission = this.saveNewPermission("User Management",systemOrganisationAgentsUserManagementPrivileges);
            final List<Permission> systemOrganisationAgentPermissions =
                    new ArrayList<Permission>(Arrays.asList(systemOrganisationAgentManagementPermission ));

            //system Recipient permissions////////////////////
            Permission systemRecipientUserManagementPermission = this.saveNewPermission("User Management",recipientUserManagementPrivileges);
            final List<Permission> systemRecipientPermissions =
                    new ArrayList<Permission>(Arrays.asList(systemRecipientUserManagementPermission  ));


            //system Donor permissions////////////////////
            Permission systemDonorsUserManagementPermission = this.saveNewPermission("User Management", donorUserManagementPrivileges);
            final List<Permission> systemDonorsPermissions =
                    new ArrayList<Permission>(Arrays.asList(systemDonorsUserManagementPermission  ));

            //system Agent permissions////////////////////
            Permission systemAgentsUserManagementPermission = this.saveNewPermission("User Management", systemAgentsUserManagementPrivileges);
            final List<Permission> systemAgentPermissions =
                    new ArrayList<Permission>(Arrays.asList(systemAgentsUserManagementPermission  ));

            //////////////////////////////system admin permissions///////////////////////////////////////////////////////
            Permission systemAdminUserManagementPermission = this.saveNewPermission("User Management", systemAdminUserManagementPrivileges);
            Permission systemAdminAppVariavlesManagementPermission = this.saveNewPermission("App Variables Management", systemAdminAppVariableManagementPriviledges);
            final List<Permission> systemAdminPermissions =
                    new ArrayList<Permission>(Arrays.asList(systemAdminUserManagementPermission, systemAdminAppVariavlesManagementPermission));


            this.saveNewRole(RoleName.ROLE_SYSTEM, "Blood Management System Administrator", false, systemAdminPermissions);
            this.saveNewRole(RoleName.ROLE_CUSTOMER_DONOR, "Blood  Donor", false, systemDonorsPermissions);
            this.saveNewRole(RoleName.ROLE_BACK_OFFICE_ADMIN, "Back Office Admin", false, systemAdminPermissions);
            this.saveNewRole(RoleName.ROLE_BACK_OFFICE_AGENT, "Back Office Agent", false, systemAgentPermissions);
            this.saveNewRole(RoleName.ROLE_ORGANIZATION_AGENT, "Organisation Agent", false, systemAdminPermissions);
            this.saveNewRole(RoleName.ROLE_RECIPIENT, "Blood Management Recipient", false, systemRecipientPermissions);

        }
    }
}