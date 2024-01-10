package com.fransica.cardscheme.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.context.annotation.Bean;

@Builder
public class CardVerificationResponse {
    @JsonProperty("scheme")
    private String scheme;
    @JsonProperty("type")
    private String type;
    @JsonProperty("bank")
    private String bank;
}
