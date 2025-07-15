package com.gyanpath.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl   {

	private String secretKey;

	public JwtServiceImpl() {
		this.secretKey = generateSecretKey();
	}

	private String generateSecretKey() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey secretKey = keyGen.generateKey();
			return Base64.getEncoder().encodeToString(secretKey.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			System.out.println("err" + e.getMessage());
			throw new RuntimeException("Error while generating secret key", e);
		}
	}





	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}


	public String extractUserName(String token) {
		return extractAllClaims(token).getSubject();
	}

	public String extractEmail(String token){
		Claims claims = extractAllClaims(token);
		return (String) claims.get("email");
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
	}

	public Claims getClaimsFromToken(String token) {
	    return extractAllClaims(token);
	}

	public boolean validateToken(String token) {
		if (!isTokenExpired(token)) {
			return true;
		}
		return false;
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}


	public List<String> extractRoles(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
		return (List<String>) claims.get("roles");
	}
}
