package com.vcreative.core.api.validators;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class DateValidator {
    public static final String DATE_FORMAT_PATTERN = "yyyyMMdd";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DateValidator.DATE_FORMAT_PATTERN);

    private DateValidator() {
    }

    public static void validateDateFormat(final String date) {
        if (StringUtils.isBlank(date)) {
            return;
        }

        try {
            DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN).parse(date);
        } catch (DateTimeParseException e) {
            log.error("Failed parsing incoming date. date={}", date, e);
            throw new InvalidParameterException(
                    "Wrong data format. Please enter date in the following format [" + DATE_FORMAT_PATTERN + "]");
        }
    }

    public static Instant validateInstant(String literal) {
        if (StringUtils.isBlank(literal)) {
            return null;
        }
        try {
            return Instant.parse(literal);
        } catch (DateTimeParseException x) {
            throw new InvalidParameterException(
                    "Wrong data format. Please enter date in the following format " + "[yyyy-MM-dd'T'HH:mm:ss.Z]");
        }

    }

    public static String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)).trim();
    }
}
