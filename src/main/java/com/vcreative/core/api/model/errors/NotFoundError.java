package com.vcreative.core.api.model.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 *  Not found error.
 *
 * @author Slavi Mihaylov
 */
@lombok.Data
public class NotFoundError {

    @ApiModelProperty(example = "2020-01-21T10:42:55.100+0000",
            position = 0)
    @JsonProperty("timestamp")
    private String timeStamp;

    @ApiModelProperty(example = "404",
            position = 0)
    private int status;

    @ApiModelProperty(example = "Not Found",
            position = 1)
    private String error;

    @ApiModelProperty(example = "Request method 'POST' not supported",
            position = 3)
    private String message;

    @ApiModelProperty(example = "<URI Path>",
            position = 4)
    private String path;

    public NotFoundError() {
    }
}
