package com.vcreative.core.api.service.impl;

import com.vcreative.core.api.service.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * REST client for the platform API
 *
 * @author Slavi Mihaylov
 */
@Slf4j
@Service
public class LookupService implements ApiClient {
    private final RestTemplate restTemplate;

    private static final String CORE_API_VCREATIVE_ENDPOINT = "/vcreative/";

    @Autowired
    public LookupService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String test() {
            return "Hello World !!!";
    }
}
