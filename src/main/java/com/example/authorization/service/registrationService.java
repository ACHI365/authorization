package com.example.authorization.service;

import com.example.authorization.models.User;
import com.example.authorization.repository.UserRepository;
import com.example.authorization.security.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class registrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public registrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            return false;

        String encodedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);
        return true;
    }

//    @Transactional
//    public void changeUserRole(String username, int id){
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(
//                "Such User does not exist"
//        ));
//
//        user.setRole(id == 0 ? UserRole.STUDENT : UserRole.ADMIN);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(newAuth);
//        userRepository.save(user);
//    }
}
