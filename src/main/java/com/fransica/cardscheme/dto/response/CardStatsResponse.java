package com.fransica.cardscheme.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CardStatsResponse {
    private boolean success;
    private int start;
    private int limit;
    private int size;
    private Map<String,String> payload;
}
