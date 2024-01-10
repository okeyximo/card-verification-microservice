package com.fransica.cardscheme.service;

import com.fransica.cardscheme.dto.request.AuthRequest;
import com.fransica.cardscheme.dto.request.UserRegistrationDto;
import com.fransica.cardscheme.dto.response.AuthResponse;
import com.fransica.cardscheme.dto.response.BaseResponse;

public interface AuthService {
    BaseResponse<String> createUser(UserRegistrationDto userDto);

    BaseResponse<AuthResponse> login(AuthRequest authRequest);
}
