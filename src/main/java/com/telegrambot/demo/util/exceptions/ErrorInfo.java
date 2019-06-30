package com.telegrambot.demo.util.exceptions;

import lombok.Data;

@Data
public class ErrorInfo {
    private String url;
    private ErrorType type;
    private String message;


    public ErrorInfo(CharSequence url, ErrorType type, String message) {
        this.url = url.toString();
        this.type = type;
        this.message = message;
    }
}
