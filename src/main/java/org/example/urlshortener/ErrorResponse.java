package org.example.urlshortener;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime time;
    private int status;
    private String message;

    public LocalDateTime getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse(LocalDateTime time, int status, String message){
        this.time = time;
        this.message = message;
        this.status = status;
    }
}
