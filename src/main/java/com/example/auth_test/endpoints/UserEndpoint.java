package com.example.auth_test.endpoints;

import com.example.auth_test.requests.UserInfoResponse;
import com.example.auth_test.services.tokenService.AccessTokenService;
import com.example.auth_test.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserEndpoint {
    private final AccessTokenService tokenService;
    private final UserService userService;
    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> aboutMe(HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("ACCESS-TOKEN"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow();
        UserInfoResponse response = userService.getUserInfo(tokenService.extractEmail(token));
        return ResponseEntity.ok(response);
    }
}
