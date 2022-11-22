package com.kgisl.auth.contoller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.kgisl.auth.entity.AuthResponse;
import com.kgisl.auth.entity.User;
import com.kgisl.auth.util.JwtTokenUtil;
import com.kgisl.auth.config.JwtTokenFilter;
import com.kgisl.auth.entity.AuthRequest;
 
@RestController
@RequestMapping("/api")
public class AuthApi {
    @Autowired 
    AuthenticationManager authManager;
    @Autowired 
    JwtTokenFilter jwtTokenFilter;
    @Autowired 
    JwtTokenUtil jwtTokenUtil;
     
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()) );
             
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
             
            return ResponseEntity.ok().body(response);
             
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}