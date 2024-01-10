package com.fransica.cardscheme.service;

import com.fransica.cardscheme.dto.response.BaseResponse;
import com.fransica.cardscheme.dto.response.CardStatsResponse;
import com.fransica.cardscheme.dto.response.CardVerificationResponse;

public interface CardDetailsService {
    BaseResponse<CardVerificationResponse> verifyCard(String cardNumber);

    CardStatsResponse getCardStats(int start, int limit);
}
