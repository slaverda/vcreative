package com.vcreative.core.api.utils;

import com.vcreative.core.api.model.dto.SimplePageRequest;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.security.InvalidParameterException;
@Slf4j
public final class PageParamsUtils {
    private PageParamsUtils() {
    }

    public static void validatePageNumberSize(final Integer pageNumber, final Integer pageSize) {
        if (pageNumber < 0) {
            log.info("Wrong page_number entered. page={}", pageNumber);
            throw new InvalidParameterException(
                    "Invalid input for 'page_number'. Please provide integer in this range [0 - 2147483647]");
        }
        if (pageSize != null && pageSize < 1) {
            log.info("Wrong page_size entered. size={}", pageSize);
            throw new InvalidParameterException(
                    "Invalid input for 'page_size'. Please provide integer in this range [1 - 2147483647]");
        }
    }

    public static SimplePageRequest toPageRequest(@Valid Integer pageNumber, @Valid Integer pageSize) {
        return SimplePageRequest.of(
                pageSize != null ? pageNumber : 0,
                pageSize != null ? pageSize : Integer.MAX_VALUE);
    }
}
