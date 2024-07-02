package com.example.GSRegistation.repository;

import com.example.GSRegistation.model.UserEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntitiy, Long> {
    boolean existsByUsername(String username);
    boolean existsByAccountNumber(String accountNumber);
    Optional<UserEntitiy> findByUsername(String username);
}
