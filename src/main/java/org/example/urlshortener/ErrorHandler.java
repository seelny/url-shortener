package org.example.urlshortener;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorResponse> errorCather(UrlNotFoundException exception){
        ErrorResponse urlNotFound = new ErrorResponse(LocalDateTime.now(), 404,  "URL not found");
        return ResponseEntity.status(404).body(urlNotFound);
    }
}
