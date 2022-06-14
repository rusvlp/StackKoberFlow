package com.gachisquad.stackkoberflow.services;


import com.gachisquad.stackkoberflow.entity.User;
import com.gachisquad.stackkoberflow.enums.Role;
import com.gachisquad.stackkoberflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public User getUserById(Long id){
        return userRepository.getById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public boolean createUser(User user){
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null){
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}" + email);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
