package com.vcreative.core.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * The base controller
 *
 * @author Slavi Mihaylov
 */
@Slf4j
@Component
@RequestMapping("${api.path:}")
public class BaseController {
    @ExceptionHandler({ ResponseStatusException.class})
    public ResponseEntity responseStatusExceptionHandler(final ResponseStatusException exception) {
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }
}
