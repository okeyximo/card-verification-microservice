package com.fransica.cardscheme.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class AuthResponse  {
    private String token;
    private Date tokenExpiration;
}
