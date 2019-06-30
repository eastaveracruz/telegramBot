package com.telegrambot.demo.util.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionInfoHandler {

    @ExceptionHandler
    public ErrorInfo exception(HttpServletRequest req, Exception e) {
        return new ErrorInfo(req.getRequestURL(), ErrorType.APP_ERROR, "bad request");
    }
}
