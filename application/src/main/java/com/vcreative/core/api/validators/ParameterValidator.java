package com.vcreative.core.api.validators;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.join;

import com.vcreative.core.api.utils.PageParamsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ParameterValidator {
    private static final int BROAD_PARAMETERS_PAGE_SIZE = 100;
    private final List<Boolean> broadParameters = new ArrayList<>();
    private final List<Boolean> otherParameters = new ArrayList<>();
    private Integer pageSize;

    public static ParameterValidator validator() {
        return new ParameterValidator();
    }

    public void validateBroadParametersHavePaging(String... parameterNames) {
        boolean noOtherParameters = allAreTrue(otherParameters);
        boolean noBroadParameters = allAreTrue(broadParameters);

        if (noOtherParameters) {
            if (noBroadParameters) {
                throw new IllegalArgumentException("At least one of the parameters is required");
            }
            if (pageSize == null || pageSize > BROAD_PARAMETERS_PAGE_SIZE) {
                final List<String> params = Arrays.asList(parameterNames);
                final String message =
                        "{PARAMS} [{NAMES}] {BE} too broad. You should use {PRONOUN} with page_size <= {MAX_SIZE}."
                                .replace("{PARAMS}", params.size() == 1 ? "Parameter" : "Parameters")
                                .replace("{NAMES}", join(params, ", "))
                                .replace("{BE}", params.size() == 1 ? "is" : "are")
                                .replace("{PRONOUN}", params.size() == 1 ? "it" : "them")
                                .replace("{MAX_SIZE}", String.valueOf(BROAD_PARAMETERS_PAGE_SIZE));
                throw new IllegalArgumentException(message);
            }
        }
    }

    public void validateOtherParametersArePassed(String message) {
        boolean noOtherParameters = allAreTrue(otherParameters);
        boolean noBroadParameters = allAreTrue(broadParameters);

        if (noOtherParameters) {
            if (noBroadParameters) {
                throw new IllegalArgumentException("At least one of the parameters is required");
            }
            throw new IllegalArgumentException(message);
        }
    }

    public final ParameterValidator broadCountry(List<String> list) {
        validateListParameter(list, "country", country -> {
            if (country.trim().length() != 3 || country.trim().contains(" ")) {
                throw new InvalidParameterException(
                        "Wrong data format [" + country + "]. Please enter valid 3 character ISO country code");
            }
        });
        broadParameters.add(CollectionUtils.isEmpty(list));
        return this;
    }

    public ParameterValidator broad(String param) {
        broadParameters.add(StringUtils.isBlank(param));
        return this;
    }

    public ParameterValidator other(String condition) {
        otherParameters.add(StringUtils.isBlank(condition));
        return this;
    }

    public ParameterValidator other(List<String> list, String parameterName) {
        validateListParameter(list, parameterName);
        otherParameters.add(CollectionUtils.isEmpty(list));
        return this;
    }

    public ParameterValidator otherDate(String dateLiteral) {
        DateValidator.validateDateFormat(dateLiteral);
        otherParameters.add(StringUtils.isBlank(dateLiteral));
        return this;
    }

    public ParameterValidator otherInstant(String instantLiteral) {
        DateValidator.validateInstant(instantLiteral);
        otherParameters.add(StringUtils.isBlank(instantLiteral));
        return this;
    }

    public ParameterValidator paging(Integer pageNumber, Integer pageSize) {
        PageParamsUtils.validatePageNumberSize(pageNumber, pageSize);
        this.pageSize = pageSize;
        return this;
    }

    private Boolean allAreTrue(List<Boolean> otherParameters) {
        return otherParameters.stream()
                .reduce(true, (accumulator, value) -> accumulator && value);
    }

    @SafeVarargs
    private void validateListParameter(List<String> list,
                                       String parameterName,
                                       Consumer<String>... additionalElementValidators) {
        if (list != null) {
            if (list.isEmpty()) {
                throw new InvalidParameterException(
                        format("Parameter '%s' cannot contain empty elements", parameterName));
            }
            for (String element : list) {
                if (io.micrometer.core.instrument.util.StringUtils.isBlank(element)) {
                    throw new InvalidParameterException(
                            format("Parameter '%s' cannot contain empty elements", element));
                }
                for (Consumer<String> additionalElementValidator : additionalElementValidators) {
                    additionalElementValidator.accept(element);
                }
            }
        }
    }
}
