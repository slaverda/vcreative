package com.vcreative.core.api.model.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 *  Internal server error.
 *
 * @author Slavi Mihaylov
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class InternalServerError {

    @ApiModelProperty(example = "2019-08-28T13:41:37.337+0000",
                      position = 0)
    @JsonProperty("timestamp")
    private String timeStamp;

    @ApiModelProperty(example = "500",
                      position = 1)
    private int status;

    @ApiModelProperty(example = "Internal Server Error",
                      position = 2)
    private String error;

    @ApiModelProperty(example = "Internal Server Error",
                      position = 3)
    private String message;

    @ApiModelProperty(example = "<URI Path>",
                      position = 4)
    private String path;
}
