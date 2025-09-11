package com.gym.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AppUserService appUserService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Skip JWT check for public endpoints
        if (path.startsWith("/api/auth/") || path.startsWith("/images/")
                || path.startsWith("/css/") || path.startsWith("/js/")) {
            filterChain.doFilter(request, response);
            return;
        }
        try{
            String bearerToken = request.getHeader("Authorization");
            if(bearerToken==null || !bearerToken.startsWith("Bearer")){
                throw new Exception("Authorization Bearer not found");
            }

            String jwt = bearerToken.substring(7);
            Claims claims = jwtService.getTokenClaims(jwt);

            if(claims==null){
                throw new Exception("Token not valid");
            }

            //JWT token is valid
            String email = claims.getSubject();
            var userDetails = appUserService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch(Exception e){
            System.out.println("Cannot authenticate user:"+e.getMessage());
        }
        filterChain.doFilter(request,response);

    }
}
