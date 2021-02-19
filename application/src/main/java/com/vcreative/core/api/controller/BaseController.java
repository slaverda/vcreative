package com.vcreative.core.api.controller;

import com.vcreative.core.api.model.dto.ErrorResponse;
import com.vcreative.core.api.model.exceptions.NotFoundException;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;

/**
 * The base controller
 *
 * @author Slavi Mihaylov
 */
@Slf4j
@Component
@RequestMapping("${api.path:}")

public class BaseController {
    static final int COUNTRY_NAME_LENGTH = 3;

    @ExceptionHandler({
            InvalidParameterException.class,
            IllegalArgumentException.class,
            MissingServletRequestParameterException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidParameterException(final Exception exception,
                                                         final HttpServletRequest request) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.buildErrorResponse(HttpStatus.BAD_REQUEST, exception, request);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(final NotFoundException exception, final HttpServletRequest request) {
        log.error(exception.getMessage(), exception);
        return ErrorResponse.buildErrorResponse(HttpStatus.NOT_FOUND, exception, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadArgumentsException(final MethodArgumentTypeMismatchException exception,
                                                     final HttpServletRequest request) {
        log.error(exception.getMessage(), exception);

        // A new exception is created in order to hide the stacktrace in the
        // MethodArgumentTypeMismatchException's message
        Exception noStackTraceException;
        if (exception.getMessage().contains("NumberFormatException")) {
            String exceptionMessage = "Failed to parse: "
                    + exception.getMessage().substring((exception.getMessage().lastIndexOf(":") + 3),
                                                       (exception.getMessage().length() - 1))
                    + ". Input should be an integer.";
            noStackTraceException = new Exception(exceptionMessage);
        } else {
            noStackTraceException = new Exception(
                    exception.getMessage().substring(exception.getMessage().lastIndexOf(":") + 2));
        }

        return ErrorResponse.buildErrorResponse(HttpStatus.BAD_REQUEST, noStackTraceException, request);
    }

    void assertIdNotBlank(String id) {
        Assert.isTrue(StringUtils.isNotBlank(id), "Wrong data format. Please enter valid ID.");
    }
}
