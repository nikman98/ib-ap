package com.stackroute.keepnote.jwtfilter;


import org.springframework.http.HttpMethod;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;



/* This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.  
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */


public class JwtFilter extends GenericFilterBean {

	
	
	

	/*
	 * Override the doFilter method of GenericFilterBean.
     * Retrieve the "authorization" header from the HttpServletRequest object.
     * Retrieve the "Bearer" token from "authorization" header.
     * If authorization header is invalid, throw Exception with message. 
     * Parse the JWT token and get claims from the token using the secret key
     * Set the request attribute with the retrieved claims
     * Call FilterChain object's doFilter() method */
	
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	HttpServletRequest httprequest=(HttpServletRequest) request;
		HttpServletResponse httpresponse=(HttpServletResponse) response;
		
		
		httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
		httpresponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");;
		httpresponse.setHeader("Access-Control-Allow-Credential", "true");
		httpresponse.setHeader("Access-Control-Allow-Headers", "*");
		
		if (httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
		{
			chain.doFilter(httprequest, httpresponse);
			
		}
		
		else {
		String authHeader=httprequest.getHeader("Authorization");
		
		if(authHeader==null || (!authHeader.startsWith("Bearer")))
			throw  new ServletException("JWT token is missing in authorization");
		
		else{
		String token=authHeader.substring(7);
			try{
				JwtParser myparser=Jwts.parser().setSigningKey("ibmkey");
				Jwt jwtobj=myparser.parse(token);
				Claims claims=(Claims)jwtobj.getBody();
				String user=claims.getSubject();
				HttpSession session=httprequest.getSession();
				session.setAttribute("username", user);
			}
			catch(SignatureException excep){
				throw new ServletException("signature mismatch/ token expired");
			}
			catch(MalformedJwtException excep){
				throw new ServletException("modified token. illegal operation");
			}
		}
		chain.doFilter(httprequest, httpresponse);
    }	

    }
    }

