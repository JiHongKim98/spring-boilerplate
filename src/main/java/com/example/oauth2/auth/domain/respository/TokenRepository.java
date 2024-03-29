package com.example.oauth2.auth.domain.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oauth2.auth.domain.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

	Optional<Token> findByTokenId(String tokenId);
}
