package com.cdpo_spring_developer.service.repository;

import com.cdpo_spring_developer.service.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username);
    boolean existsByUsername(String username);
    @Query(nativeQuery = true,
    value = "UPDATE ApplicationUser" +
            "SET userRole = 'ROLE_OPERATOR'" +
            "WHERE id = :id")
    void appointOperator(int id);

}
