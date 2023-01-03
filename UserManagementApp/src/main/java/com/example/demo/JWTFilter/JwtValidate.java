package com.example.demo.JWTFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.Model.UserExceptionCustom;
import com.example.demo.Service.UserServiceImpl;
import com.example.demo.Util.JWTUtil;


@Component
public class JwtValidate extends OncePerRequestFilter
{
	
	@Autowired
	private JWTUtil jwtutil;
	
	@Autowired
	private UserServiceImpl userservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException{
		
		
			String jwtToken = getJWTfromHeaderToken(request);
		
			try {
				if(jwtToken!= null && jwtutil.validateToken(jwtToken)) {
					
					String username = jwtutil.getUsernameFromJwtToken(jwtToken);
					UserDetails userDetails = userservice.getByUsername(username);
					
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				            userDetails,
				            null,
				            userDetails.getAuthorities()
				        );

				        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

				    } 
			

		        filterChain.doFilter(request, response);
		        
			}
			catch (UserExceptionCustom e) {
				System.out.println("catch block");
				
				response.setStatus(e.getStatus().value());
				response.getWriter().write(e.getMessage());
			} 
			
		
		}
		

	
	private String getJWTfromHeaderToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token!= null && token.startsWith("Bearer "))
			return token.substring(7);
		
		return null;
		
	}
	
}
