package com.abhijit.SpringSecurity.service;

import com.abhijit.SpringSecurity.model.Users;
import com.abhijit.SpringSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authMannager;

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return user;
    }

    public String verify(Users user) {
        Authentication authentication=
                authMannager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }else {
            return "fail";
        }
    }
}
