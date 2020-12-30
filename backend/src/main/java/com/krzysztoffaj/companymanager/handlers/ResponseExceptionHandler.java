package com.krzysztoffaj.companymanager.handlers;

import com.krzysztoffaj.companymanager.exceptions.badrequest.GenericBadRequestException;
import com.krzysztoffaj.companymanager.exceptions.conflict.GenericConflictException;
import com.krzysztoffaj.companymanager.exceptions.notfound.GenericNotFoundException;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericBadRequestException.class)
    protected ResponseEntity<ErrorResponse> badRequest(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericNotFoundException.class)
    protected ResponseEntity<ErrorResponse> notFound(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GenericConflictException.class)
    protected ResponseEntity<ErrorResponse> conflict(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, HttpStatus.CONFLICT);
    }


    private ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(ex, request.getRequestURL().toString());
        return new ResponseEntity<>(errorResponse, status);
    }

    @Getter
    @ToString
    static class ErrorResponse {

        private final String name;
        private final String url;

        ErrorResponse(Exception ex, String url) {
            this.name = ex.getClass().getSimpleName();
            this.url = url;
        }

    }

}
