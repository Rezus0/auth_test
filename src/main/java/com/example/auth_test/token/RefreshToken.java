package com.example.auth_test.token;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class RefreshToken {
    @Id
    @GeneratedValue
    private Long id;
    private String body;
    @Transient
    @Value("${token.refresh.expiryMs}")
    private Long expiryTimeMillis;
}
