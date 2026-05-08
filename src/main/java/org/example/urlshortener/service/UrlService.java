package org.example.urlshortener.service;

import org.example.urlshortener.ShortenedUrlBuilder;

import org.example.urlshortener.UrlNotFoundException;
import org.example.urlshortener.model.ShortUrl;
import org.example.urlshortener.repository.UrlRepository;
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

    public String getByShortCode(String code) {
        return urlRepository.findByShortCode(code).orElseThrow(() -> new UrlNotFoundException("URL not found")).getOriginalUrl();
    }
}
