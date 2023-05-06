package com.example.auth_test.repos;

import com.example.auth_test.model.statement.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatementRepository extends JpaRepository<Statement, Long> {

}
