package com.example.auth_test.endpoints;

import com.example.auth_test.model.user.User;
import com.example.auth_test.requests.ProfileResponse;
import com.example.auth_test.requests.StatementRequest;
import com.example.auth_test.requests.UpdateProfileRequest;
import com.example.auth_test.requests.UserInfoResponse;
import com.example.auth_test.services.CookieService;
import com.example.auth_test.services.StatementService;
import com.example.auth_test.services.StudentProfileService;
import com.example.auth_test.services.tokenService.AccessTokenService;
import com.example.auth_test.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserEndpoint {
    private final AccessTokenService tokenService;
    private final UserService userService;
    private final CookieService cookieService;
    private final StatementService statementService;
    private final StudentProfileService studentProfileService;

    @GetMapping("/me")
    public ResponseEntity<UserInfoResponse> aboutMe(HttpServletRequest request) {
        String token = cookieService.extractToken(request);
        UserInfoResponse response = userService.getUserInfo(tokenService.extractEmail(token));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileResponse> profile(HttpServletRequest httpRequest,
                                                   @RequestBody UpdateProfileRequest request) {
        User user = extractUserFromRequest(httpRequest);
        return ResponseEntity.ok(studentProfileService.updateProfile(user, request));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<String> addStatement(@RequestBody StatementRequest request,
                                               HttpServletRequest httpRequest) {
        User user = extractUserFromRequest(httpRequest);
        statementService.save(request, user);
        return ResponseEntity.ok("Statement has been successfully added");
    }

    private User extractUserFromRequest(HttpServletRequest request) {
        String token = cookieService.extractToken(request);
        return userService.getUser(tokenService.extractEmail(token));
    }

}
