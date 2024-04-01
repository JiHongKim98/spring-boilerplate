package com.example.demo.auth.domain.respository;

import java.util.Optional;

import com.example.demo.auth.domain.Token;

public interface TokenRepository {

	void save(Token token);

	void deleteByTokenId(String tokenId);

	Optional<Token> findByTokenId(String tokenId);
}
