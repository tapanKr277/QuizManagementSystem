package com.gyanpath.security.repo;

import com.gyanpath.security.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtTokenRepo extends JpaRepository<JwtToken, Short> {

    List<JwtToken> findByUserId(Short userId);

    List<JwtToken> findByUserIdAndBlacklisted(Short userId, Boolean blacklisted);

    JwtToken findByToken(String token);
}
