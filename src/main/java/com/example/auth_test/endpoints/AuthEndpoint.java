package com.example.auth_test.endpoints;

import com.example.auth_test.requests.RegisterRequest;
import com.example.auth_test.services.UserService;
import com.example.auth_test.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthEndpoint {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(userService.save(request));
    }

}
