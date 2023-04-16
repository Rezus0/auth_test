package com.example.auth_test.services;

import com.example.auth_test.exceptions.UserAlreadyExistsException;
import com.example.auth_test.repos.UserRepository;
import com.example.auth_test.requests.RegisterRequest;
import com.example.auth_test.user.Role;
import com.example.auth_test.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String save(RegisterRequest request) {
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.WORKER);
        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        if (userRepository.findUserByEmail(user.getEmail()).isPresent())
            throw new UserAlreadyExistsException();
        userRepository.save(user);
        return "User has been successfully added";
    }

}
