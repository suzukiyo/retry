package com.example.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ExceptionAdvice {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT)
    @ExceptionHandler(value = {
            TimeoutException.class,
            ExecutionException.class,
            InterruptedException.class
    })
    public Map<String, Object> timeout() {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", "タイムアウトしました");
        errorMap.put("status", HttpStatus.REQUEST_TIMEOUT);
        return errorMap;
    }

}