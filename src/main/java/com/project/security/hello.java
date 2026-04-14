package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; 
import org.springframework.security.core.AuthenticationException; 
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class hello {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/hello")
    public String getMessage() {
        return "hello";
    }

    @GetMapping("/user/hello")
    public String sayUserHello() {
        return "hello, User";
    }

    @GetMapping("/admin/hello")
    public String sayAdminHello() {
        return "hello, Admin";
    }

    @PostMapping("/signin")
    public String login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "Invalid username or password"; 
        }

        //set authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //generate JWT
        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        return jwtToken;
    }
}