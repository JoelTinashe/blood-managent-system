package com.digitalkrapht.bloodbank.bloodbank.security.user;

import com.digitalkrapht.bloodbank.bloodbank.users.models.User;
import com.digitalkrapht.bloodbank.bloodbank.users.repository.UserRepository;
import com.digitalkrapht.bloodbank.bloodbank.utils.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // Let people login with either username or headquartersEmail
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or Email : " + usernameOrEmail)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByTokenHash(String hash) {
        User user = userRepository.findByTokenHash(hash).orElse(null);
        if (user != null) {
            return UserPrincipal.create(user);
        }
        return null;
    }

}

