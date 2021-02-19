package com.vcreative.core.api.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorResponse {
    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

    private String traceId;

    /**
     * Create an ErrorResponse object to return to a front end
     *
     * @param statusCode http status code
     * @param exception exception that has caused the error response
     * @param request the initial http request
     *
     * @return an ErrorResponse object
     */
    public static ErrorResponse buildErrorResponse(final HttpStatus statusCode, final Throwable exception,
                                                   final HttpServletRequest request) {
        String path =
                request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");

        return new ErrorResponse(LocalDateTime.now().toString(), statusCode.value(), statusCode.getReasonPhrase(),
                                 exception.getMessage(), path, MDC.get("X-B3-TraceId"));
    }
}
