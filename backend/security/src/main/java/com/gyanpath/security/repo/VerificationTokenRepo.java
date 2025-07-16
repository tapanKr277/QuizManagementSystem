package com.gyanpath.security.repo;

import com.gyanpath.security.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Short> {

    Optional<VerificationToken> findByToken(String token);

    void deleteByEmail(String email);

    void deleteByToken(String token);

    @Query("SELECT v FROM VerificationToken v WHERE v.token = :token AND v.email = :email")
    Optional<VerificationToken> findByTokenAndEmail(@Param("token") String token, @Param("email") String email);
}
