package com.example.ExampleCrudApiRest.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.ExampleCrudApiRest.security.entity.MiUserDetail;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication authentication) {
		MiUserDetail usuarioPrincipal = (MiUserDetail) authentication.getPrincipal();
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000)).signWith(getSecurity(secret))
				.compact();
	}

	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecurity(secret)).build().parseClaimsJws(token).getBody()
				.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSecurity(secret)).build().parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			logger.error("token no soportado");
		} catch (ExpiredJwtException e) {
			logger.error("token expirado");
		} catch (IllegalArgumentException e) {
			logger.error("token vacío");
		} /*  catch (SignatureException e) {
			logger.error("fail en la firma");
		} */
		return false;
	}

	public Key getSecurity(String secret) {
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

}
