package org.example.urlshortener.controller;

import org.example.urlshortener.dto.UrlRequest;
import org.example.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UrlController.class)
public class UrlControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlService;

    @MockitoBean
    private org.springframework.cache.CacheManager cacheManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testBadRequestWhenUrlIsInvalid() throws Exception{
        UrlRequest invalidRequest = new UrlRequest("---");
        mockMvc.perform(post("/shorten")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}
