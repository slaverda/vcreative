package com.vcreative.core.api.model.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 *  Bad request error.
 *
 * @author Slavi Mihaylov
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestError {

    @ApiModelProperty(example = "400",
                      position = 0)
    private int status;

    @ApiModelProperty(example = "Bad Request",
                      position = 1)
    private BadRequestErrors errors;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BadRequestErrors {

        @ApiModelProperty(example = "Wrong raceDate [date [YYYY-MM-DD], with range "
              + "[from 1988-01-01 to 2079-06-06]] parameter, url structure <URI Path>")
        @JsonProperty("7")
        private String message;
    }
}
