package com.restaurantproject.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil 
{
	
	public String generateToken(UserDetails userDetails)
	{
        
        return generateToken(new HashMap<>(),userDetails);
    }
  private String generateToken( Map<String,Object> extraclaims,UserDetails userDetails)
  {
	  return Jwts.builder()
				.setClaims(extraclaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date (System.currentTimeMillis()+ 1000 * 60 * 24))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
  }
  private Key getSignKey()
  {
	  byte[] keyBytes=Decoders.BASE64.decode("413F4428472B4B6250655368566D5933676397924422645294840406351");
	  return Keys.hmacShaKeyFor(keyBytes);
  }
  public String extractUserName(String token) 
  {
      return extractClaim(token, Claims::getSubject);
  }

  public Boolean isTokenvalid(String token, UserDetails userDetails)
  {
      final String userName = extractUserName(token);
      return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }


  private Boolean isTokenExpired(String token)
  {
      return extractExpiration(token).before(new Date());
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
  {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
  }
  public Date extractExpiration(String token)
  {
      return extractClaim(token, Claims::getExpiration);
  }
  

  private Claims extractAllClaims(String token) 
  {
      return Jwts
              .parserBuilder()
              .setSigningKey(getSignKey())
              .build()
              .parseClaimsJws(token)
              .getBody();
  }
	
}
