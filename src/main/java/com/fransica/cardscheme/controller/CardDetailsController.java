package com.fransica.cardscheme.controller;

import com.fransica.cardscheme.service.CardDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/card-scheme")
public class CardDetailsController {

    private final CardDetailsService cardDetailsService;

    @GetMapping("/verify/{bin}")
    public ResponseEntity<?> verifyCardData(@PathVariable String bin) {
        return new ResponseEntity<>(cardDetailsService.verifyCard(bin), HttpStatus.OK);
    }

    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(name = "start", defaultValue = "0") int start,
                                      @RequestParam(name = "limit", defaultValue = "3") int limit) {
        return new ResponseEntity<>(cardDetailsService.getCardStats(start, limit), HttpStatus.OK);

    }




}
