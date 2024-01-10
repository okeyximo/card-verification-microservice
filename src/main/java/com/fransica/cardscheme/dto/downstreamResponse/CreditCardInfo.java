package com.fransica.cardscheme.dto.downstreamResponse;

import lombok.Data;

@Data
public class CreditCardInfo {
    private NumberInfo number;
    private String scheme;
    private String type;
    private String brand;
    private boolean prepaid;
    private CountryInfo country;
    private BankInfo bank;

    @Data
    public static class NumberInfo {
        private int length;
        private boolean luhn;
    }

    @Data
    public static class CountryInfo {
        private String numeric;
        private String alpha2;
        private String name;
        private String emoji;
        private String currency;
        private double latitude;
        private double longitude;
    }

    @Data
    public static class BankInfo {
        private String name;
        private String url;
        private String phone;
        private String city;
    }
}