package com.fransica.cardscheme.service;

import com.fransica.cardscheme.dto.downstreamResponse.CreditCardInfo;
import com.fransica.cardscheme.dto.response.BaseResponse;
import com.fransica.cardscheme.dto.response.CardStatsResponse;
import com.fransica.cardscheme.dto.response.CardVerificationResponse;
import com.fransica.cardscheme.entity.AuditLog;
import com.fransica.cardscheme.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardDetailsServiceImpl implements CardDetailsService {
    private final WebClient webClient;
    private final AuditLogRepository auditLogRepository;
    private final String BIN_LOOKUP_BASE_URL = "https://lookup.binlist.net/";


    @Override
    public BaseResponse<CardVerificationResponse> verifyCard(String bin) {
        try {
            CreditCardInfo creditCardInfo = webClient.get()
                    .uri(BIN_LOOKUP_BASE_URL + bin)
                    .retrieve()
                    .bodyToMono(CreditCardInfo.class)
                    .block();
            return BaseResponse.<CardVerificationResponse>builder()
                    .success(true)
                    .payload(CardVerificationResponse.builder()
                            .scheme(creditCardInfo.getScheme())
                            .type(creditCardInfo.getType())
                            .bank(creditCardInfo.getBank().getName())
                            .build())
                    .build();

        } catch (Exception e) {
            log.error("error occurred while verifying card data", e);
            throw new RuntimeException("Error occurred while verifying card"); // todo: replace with the correct exception
        }
    }

    @Override
    public CardStatsResponse getCardStats(int start, int limit) {
        var cardStarts = auditLogRepository.findAll(PageRequest.of(start, limit));
        Map<String, String> payload = new HashMap<>();
        for (AuditLog auditLog : cardStarts.getContent()) {
            payload.put(auditLog.getBin(), auditLog.getCount().toString());
        }
        if (cardStarts.hasContent()) {
            return CardStatsResponse.builder()
                    .success(true)
                    .start(start)
                    .limit(limit)
                    .size(cardStarts.getSize())
                    .payload(payload)
                    .build();
        }
        return CardStatsResponse.builder()
                .success(true)
                .start(start)
                .limit(limit)
                .size(0)
                .payload(payload)
                .build();
    }

}
