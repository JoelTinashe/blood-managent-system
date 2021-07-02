package com.digitalkrapht.bloodbank.bloodbank.utils.generators;

import com.digitalkrapht.bloodbank.bloodbank.security.models.RoleName;
import com.digitalkrapht.bloodbank.bloodbank.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
@Service
public class StringGeneratorUtility {

    @Autowired
    UserRepository userRepository;
    private String GenerateUserId(RoleName role) {
        String userType;
        if (role.equals(RoleName.ROLE_BACK_OFFICE_ADMIN)) {
            userType = "USH";
        } else if (role.equals(RoleName.ROLE_SYSTEM)) {
            userType = "USA";
        } else if (role.equals(RoleName.ROLE_BACK_OFFICE_AGENT)) {
            userType = "USL";
        }  else if (role.equals(RoleName.ROLE_CUSTOMER_DONOR)) {
            userType = "UCU";
        }
        else if (role.equals(RoleName.ROLE_ORGANIZATION_AGENT)) {
            userType = "UOA";
        }
        else {
            userType = "BAD";
        }


        return userType.concat(String.valueOf(LocalDateTime.now().getMonthValue() + 1)).concat(Year.now().format(DateTimeFormatter.ofPattern("uu")))
                .concat("-" + generateSecureAlphaNumeric(3));
    }


    private boolean UserIdAvailable(String code) {
        return userRepository.existsById(code);
    }

    public String fetchValidUserId(RoleName role) {
        String myUserId = GenerateUserId(role);
        while (UserIdAvailable(myUserId)) {
            myUserId = GenerateUserId(role);
        }
        return myUserId;
    }
    // == user toke hash
    private String GenerateTokenHash() {

        return generateSecureAlphaNumeric(10);
    }

    private boolean TokenHashAvailable(String secret) {
        return userRepository.existsByTokenHash(secret);
    }
    private String generateSecureAlphaNumeric(int len) {
        char[] ch = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();
        char[] c = new char[len];
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < len; i++) {
            c[i] = ch[random.nextInt(ch.length)];
        }
        return new String(c);
    }
    public String fetchValidTokenHash() {
        String myHash = GenerateTokenHash();
        while (TokenHashAvailable(myHash)) {
            myHash = GenerateTokenHash();
        }
        return myHash;
    }
}
