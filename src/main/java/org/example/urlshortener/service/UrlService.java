package org.example.urlshortener.service;

import org.example.urlshortener.ShortenedUrlBuilder;

import org.example.urlshortener.UrlNotFoundException;
import org.example.urlshortener.model.ShortUrl;
import org.example.urlshortener.repository.UrlRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    public String createShortUrl(String originalUrl) {
        ShortUrl urlEntry = new ShortUrl();
        urlEntry.setOriginalUrl(originalUrl);

        urlEntry = urlRepository.save(urlEntry);

        String code = ShortenedUrlBuilder.encode((int) (long) urlEntry.getId());

        urlEntry.setShortCode(code);
        urlRepository.save(urlEntry);

        return code;
    }

    @Cacheable(value = "urls", key = "#shortCode")
    public String getByShortCode(String shortCode) {
        return urlRepository.findByShortCode(shortCode).orElseThrow(() -> new UrlNotFoundException("URL not found")).getOriginalUrl();
    }

    @Transactional
    @CacheEvict(value = "urls", key = "#a0") // idk
    public void deleteUrl(String shortCode){
        urlRepository.deleteByShortCode(shortCode);
    }

    @Async
    @Transactional
    public void trackClick(String code){
        urlRepository.incrementClicks(code);
    }
}
