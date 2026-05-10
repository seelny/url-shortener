package org.example.urlshortener.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.urlshortener.service.UrlService;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public record UrlRequest(
        @NotBlank(message = "URL cannot be empty")
        @URL(message = "Invalid URL format")
        String originalUrl
)
{
}
