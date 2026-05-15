package org.example.urlshortener.controller;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import org.example.urlshortener.dto.UrlRequest;
import org.example.urlshortener.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class UrlController {
    private final UrlService urlService;

    public UrlService getUrlService() {
        return urlService;
    }

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

//    @PostMapping("/shorten")
//    public String shorten(@RequestBody String longUrl) {
//
//        String shorten = urlService.createShortUrl(longUrl);
//
//        return shorten;
//    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String url = urlService.getByShortCode(code);
        URI uri = URI.create(url);
        urlService.trackClick(code);
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }

    @PostMapping("/shorten")
    public String shortenUrl(@Valid @RequestBody UrlRequest request) {
        return urlService.createShortUrl(request.originalUrl());
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<Void> deleteUrl(@PathVariable String shortCode) {
        urlService.deleteUrl(shortCode);
        return ResponseEntity.noContent().build(); //204
    }
}
