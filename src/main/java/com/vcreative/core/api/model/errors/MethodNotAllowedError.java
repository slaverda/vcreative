package com.vcreative.core.api.model.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
/**
 *  Method not allowed error.
 *
 * @author Slavi Mihaylov
 */
@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class MethodNotAllowedError {

    @ApiModelProperty(example = "2020-01-21T10:42:55.100+0000",
                      position = 0)
    @JsonProperty("timestamp")
    private String timeStamp;

    @ApiModelProperty(example = "405",
                      position = 1)
    private int status;

    @ApiModelProperty(example = "Method Not Allowed",
                      position = 2)
    private String error;

    @ApiModelProperty(example = "Request method 'POST' not supported",
                      position = 3)
    private String message;

    @ApiModelProperty(example = "<URI Path>",
                      position = 4)
    private String path;
}
