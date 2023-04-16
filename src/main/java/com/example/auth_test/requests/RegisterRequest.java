package com.example.auth_test.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(max = 20)
    @NotBlank
    private String name;
    @Size(max = 30)
    @NotBlank
    private String surname;
    @Email
    @Size(max = 40)
    @NotBlank
    private String email;
    @Size(min = 6, max = 50)
    @NotBlank
    private String password;
}
