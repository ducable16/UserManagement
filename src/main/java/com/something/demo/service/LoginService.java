package com.something.demo.service;

import com.something.demo.repository.UserRepository;
import com.something.demo.request.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    private final String SECRET_KEY = "n1k2byf5ymr92jtijt35dtz57q63x6p83ndxn4dyuq7c5bp6";


    public String login(
            @RequestBody LoginRequest request) {
        System.out.println("Not authenticated!!!!!!!!!!!!!!");
//        if(userRepository.findById(request.getUsername()));
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Authenticated!!!!!!!!!!!!!!");
        String token = Jwts.builder()
                .setSubject(request.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
        return token;
    }
}





