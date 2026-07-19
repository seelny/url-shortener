package org.example.urlshortener;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShortenedUrlBuilderTests {
    @Test
    public void testEncodeValidId(){
        String result = ShortenedUrlBuilder.encode(1);
        assertEquals("1", result);
    }

    @Test
    public void testEncodeExceptionThrowInvalidId(){
        assertThrows(RuntimeException.class, () -> {ShortenedUrlBuilder.encode(0);});
    }
}
