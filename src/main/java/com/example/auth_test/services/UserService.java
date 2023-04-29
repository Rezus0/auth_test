package com.example.auth_test.services;

import com.example.auth_test.repos.UserRepository;
import com.example.auth_test.requests.UserInfoResponse;
import com.example.auth_test.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserInfoResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .build();
    }

    public User getUser(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void confirmAccount(String email) {
        userRepository.updateConfirmedForEmail(email);
    }

    public boolean isUserExists(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
