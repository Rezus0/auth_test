package com.example.auth_test.services;

import com.example.auth_test.model.statement.Statement;
import com.example.auth_test.model.user.User;
import com.example.auth_test.repos.StatementRepository;
import com.example.auth_test.requests.StatementRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatementService {
    private final StatementRepository statementRepository;

    public void save(StatementRequest request, User user) {
        Statement statement = Statement.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .creator(user)
                .date(LocalDateTime.now())
                .build();
        statementRepository.save(statement);
    }
}
