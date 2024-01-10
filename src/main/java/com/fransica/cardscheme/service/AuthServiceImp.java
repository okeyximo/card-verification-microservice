package com.fransica.cardscheme.service;

import com.fransica.cardscheme.dto.request.AuthRequest;
import com.fransica.cardscheme.dto.request.UserRegistrationDto;
import com.fransica.cardscheme.dto.response.AuthResponse;
import com.fransica.cardscheme.dto.response.BaseResponse;
import com.fransica.cardscheme.entity.User;
import com.fransica.cardscheme.repository.UserRepository;
import com.fransica.cardscheme.config.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImp implements AuthService{
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public BaseResponse<String> createUser(UserRegistrationDto userDto){
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("user already exits"); // todo: replace with the right exception
        }
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        userRepository.save(user);
        return BaseResponse.<String>builder()
                .success(true)
                .payload("User created successfully")
                .build();
    }

    @Override
    public BaseResponse<AuthResponse> login(AuthRequest authRequest){
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException("Invalid username or password"); // todo: replace with the right exception
//            throw new ResourceNotFoundException("Invalid username or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        User appUserEntity = userRepository.findByUsername(authRequest.getUsername()).orElseThrow(() -> new RuntimeException("Userdoes not exit"));

        String token = jwtService
                .generateToken(new org.springframework.security.core.userdetails
                        .User(authRequest.getUsername(), authRequest.getPassword(), new ArrayList<>()));
        return BaseResponse.<AuthResponse>builder()
                .success(true)
                .payload(AuthResponse.builder()
                        .token(token)
                        .tokenExpiration(jwtService.getExpirationDateFromToken(token))
                        .build())
                .build();

    }

}
