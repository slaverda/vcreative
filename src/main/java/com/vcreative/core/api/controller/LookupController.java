package com.vcreative.core.api.controller;

import com.vcreative.core.api.model.errors.InternalServerError;
import com.vcreative.core.api.model.errors.MethodNotAllowedError;
import com.vcreative.core.api.model.errors.NotFoundError;
import com.vcreative.core.api.service.ApiClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
/**
 * The Lookup controller
 *
 * @author Slavi Mihaylov
 */
@Slf4j
@RestController
public class LookupController extends BaseController {

    private final ApiClient apiClient;

    @Autowired
    public LookupController(final ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @ApiOperation("Register positive and negative votes for each post ")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "Method Not Allowed", response =
                    MethodNotAllowedError.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Not Found", response =
                    NotFoundError.class),
            @ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Internal Server "
                    + "Error", response = InternalServerError.class) })
    @GetMapping(value = "/lookup/test")
    public String registerVotes() {
        log.info("Test is successful");
        return apiClient.test();
    }
}
