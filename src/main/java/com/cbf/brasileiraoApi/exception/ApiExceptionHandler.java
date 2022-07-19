package com.cbf.brasileiraoApi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected StandardError processNotFoundException(
            final ApiException ex, final WebRequest request) {
        return StandardError.builder()
                .message(ex.getIssue().getMessage())
                .timesTamp(System.currentTimeMillis())
                .path(request.getDescription(true))
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected StandardError processBadRequestException(
            final ApiException ex, final WebRequest request) {
        return StandardError.builder()
                .message(ex.getIssue().getMessage())
                .timesTamp(System.currentTimeMillis())
                .path(request.getDescription(true))
                .build();
    }
}