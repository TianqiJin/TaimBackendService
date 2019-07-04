package com.taim.taimbackendservice.configuration;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
public class ExceptionHandlerConfiguration extends ResponseEntityExceptionHandler {

    @ControllerAdvice
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public static class DetailedExceptionHandler{
        @ExceptionHandler({JsonMappingException.class})
        public ResponseEntity notFoundExceptionHandler(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionUtils.getRootCauseMessage(ex));
        }
    }

    @ControllerAdvice
    @Order
    public static class DefaultExceptionHandler{
        @ExceptionHandler({Exception.class})
        public ResponseEntity generalExceptionHandler(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionUtils.getRootCauseMessage(ex));
        }
    }
}
