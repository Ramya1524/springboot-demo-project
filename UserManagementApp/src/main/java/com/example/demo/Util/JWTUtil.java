package com.example.demo.Util;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.example.demo.Model.UserDto;
import com.example.demo.Model.UserExceptionCustom;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JWTUtil {
	
	@Value("${app.jwtexpiry-inmilliseconds}")
	int expiryTime;
	
	@Value("${app.jwt-secrets}")
	String secretKey;
	
	public String generateToken(UserDto user)throws ServletException, Exception
	{
		
		if(user.getUsername()==null || user.getPassword()==null)
		{
			throw new ServletException("Please enter a valid username and password");
		}
		Date current = new Date();
		
		//ExpiryTime is set to 15 min from issued date
		Date expiryDate = new Date(current.getTime() + expiryTime);
		
			String jwtToken=Jwts.builder()
					.setSubject(user.getUsername())
					.setIssuedAt(current)
					.setExpiration(expiryDate)
					.signWith(SignatureAlgorithm.HS256, secretKey)
					.compact();
		
		return jwtToken;
	}
	
	public String getUsernameFromJwtToken(String token) {
		
		Claims claim = Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(token)
				.getBody();
				
		return claim.getSubject();
		
	}
	
	public boolean validateToken(String token) throws UserExceptionCustom {
		
		try {
			Jwts.parser()
			.setSigningKey(secretKey)
			.parseClaimsJws(token);
			
			return true;
			
		}
		catch(SignatureException e) {
			throw new UserExceptionCustom("Invalid access", HttpStatus.BAD_REQUEST);
		}
		catch(MalformedJwtException e) {
			throw new UserExceptionCustom("Token received is not authorized", HttpStatus.BAD_REQUEST);
		}
		catch(ExpiredJwtException e) {
			throw new UserExceptionCustom("Token is expired, try to Login again", HttpStatus.UNAUTHORIZED);
		}
		catch(UnsupportedJwtException e) {
			throw new UserExceptionCustom("Token received is unsupported, send a valid token", HttpStatus.BAD_REQUEST);
		}
		catch(IllegalArgumentException e) {
			throw new UserExceptionCustom("Illegal arguments received, send a valid request", HttpStatus.BAD_REQUEST);
		}
		
	}

}
