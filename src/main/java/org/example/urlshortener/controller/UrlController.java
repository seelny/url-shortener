package org.example.urlshortener.controller;

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

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestBody String longUrl) {

        String shorten = urlService.createShortUrl(longUrl);

        return shorten;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String url = urlService.getByShortCode(code);
        URI uri = URI.create(url);
        return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
    }
}
